package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_BY_ROOM;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final BookingMapper bookingMapper;

    @Override
    public RoomResponse add(RoomRequest roomRequest) {
        Room room = roomMapper.buildRoom(roomRequest);
        roomRepository.save(room);
        return roomMapper.buildRoomResponse(room, bookingMapper);

    }

    @Override
    public List<RoomResponse> read() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.buildRoomsResponse(rooms, bookingMapper);
    }

    @Override
    public RoomResponse update(Long id, RoomRequest roomRequest) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.map(room -> roomMapper.updateRoom(room, roomRequest))
                .map(roomRepository::save)
                .map(room -> roomMapper.buildRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_ROOM + id));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        optionalRoom.ifPresent(roomRepository::delete);
        return optionalRoom.isPresent();
    }

    @Override
    public RoomResponse findRoomByID(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.map(room -> roomMapper.buildRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_ROOM + id));
    }

    @Override
    public List<Room> findRoomsByIdForBooking(List<Long> list) {
        return roomRepository.findAllById(list);
    }

    @Override
    public RoomResponse findRoomByNumber(String number) {
        Optional<Room> optionalRoom = roomRepository.findRoomByNumber(number);
        return optionalRoom.map(room -> roomMapper.buildRoomResponse(room, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_ROOM + number));
    }

    @Override
    public List<RoomResponse> findRoomsByRoomCategory(RoomCategory category) {
        List<Room> rooms = roomRepository.findRoomsByRoomCategory(category);
        if (rooms.size() != 0) {
            return roomMapper.buildRoomsResponse(rooms, bookingMapper);
        } else {
            throw new EntityNotFoundException(ERROR_MESSAGE_BY_ROOM + category);
        }
    }
}