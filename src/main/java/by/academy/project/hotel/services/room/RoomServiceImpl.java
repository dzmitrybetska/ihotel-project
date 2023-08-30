package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.services.description.DescriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.utils.Constants.ROOM_NOT_FOUND_BY_ID;
import static by.academy.project.hotel.utils.Constants.ROOM_NOT_FOUND_BY_NUMBER;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final DescriptionService descriptionService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final BookingMapper bookingMapper;

    @Override
    public RoomResponse add(RoomRequest roomRequest) {
        Room room = roomMapper.mapToRoom(roomRequest);
        roomRepository.save(room);
        return roomMapper.mapToRoomResponse(room, bookingMapper);

    }

    @Override
    public List<RoomResponse> read() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.mapToRoomsResponse(rooms, bookingMapper);
    }

    @Override
    public RoomResponse update(Long id, RoomRequest roomRequest) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom
                .map(room -> roomMapper.updateRoom(room, roomRequest))
                .map(roomRepository::save)
                .map(room -> roomMapper.mapToRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public RoomResponse findRoomByID(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom
                .map(room -> room.setDescription(descriptionService.getDescription(room.getRoomCategory())))
                .map(room -> roomMapper.mapToRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public List<Room> findRoomsByIdForBooking(List<Long> list) {
        return roomRepository.findAllById(list);
    }

    @Override
    public RoomResponse findRoomByNumber(String number) {
        Optional<Room> optionalRoom = roomRepository.findRoomByNumber(number);
        return optionalRoom
                .map(room -> room.setDescription(descriptionService.getDescription(room.getRoomCategory())))
                .map(room -> roomMapper.mapToRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_NUMBER, number)));
    }

    @Override
    public List<RoomResponse> findRoomsByRoomCategory(RoomCategory category) {
        List<Room> rooms = roomRepository.findRoomsByRoomCategory(category);
        if (!rooms.isEmpty()) {
            rooms.forEach(room -> room.setDescription(descriptionService.getDescription(room.getRoomCategory())));
            return roomMapper.mapToRoomsResponse(rooms, bookingMapper);
        } else {
            throw new EntityNotFoundException(ROOM_NOT_FOUND_BY_ID + category);
        }
    }
}