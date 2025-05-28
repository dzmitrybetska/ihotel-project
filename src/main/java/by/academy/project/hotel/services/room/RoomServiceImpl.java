package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.services.description.DescriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static by.academy.project.hotel.utils.Constants.ROOM_NOT_FOUND_BY_ID;
import static by.academy.project.hotel.utils.Constants.ROOM_NOT_FOUND_BY_NUMBER;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final DescriptionService descriptionService;
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public RoomResponse add(RoomRequest roomRequest) {
        Room room = roomMapper.mapToRoom(roomRequest);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.mapToRoomResponse(savedRoom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> read() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomMapper::mapToRoomResponse)
                .map(this::addRoomDescription)
                .toList();
    }

    @Override
    @Transactional
    public RoomResponse update(Long id, RoomRequest roomRequest) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom
                .map(room -> roomMapper.updateRoom(room, roomRequest))
                .map(roomRepository::save)
                .map(roomMapper::mapToRoomResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_ID, id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse findRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom
                .map(roomMapper::mapToRoomResponse)
                .map(this::addRoomDescription)
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public List<Room> findRoomsByIdForBooking(List<Long> list) {
        return roomRepository.findAllById(list);
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse findRoomByNumber(String number) {
        Optional<Room> optionalRoom = roomRepository.findRoomByNumber(number);
        return optionalRoom
                .map(roomMapper::mapToRoomResponse)
                .map(this::addRoomDescription)
                .orElseThrow(() -> new EntityNotFoundException(format(ROOM_NOT_FOUND_BY_NUMBER, number)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> findRoomsByRoomCategory(RoomCategory category) {
        List<Room> rooms = roomRepository.findRoomsByRoomCategory(category);
        return rooms.stream()
                .map(roomMapper::mapToRoomResponse)
                .map(this::addRoomDescription)
                .toList();
    }

    @Override
    public List<RoomResponse> findAvailableRooms(LocalDate arrival, LocalDate departure) {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .filter(room -> check(room, arrival, departure))
                .map(roomMapper::mapToRoomResponse)
                .map(this::addRoomDescription)
                .toList();
    }

    private boolean check(Room room, LocalDate arrival, LocalDate departure) {
        List<Booking> bookingList = room.getBookings().stream()
                .filter(((Predicate<Booking>) booking -> arrival.isBefore(booking.getArrival())
                        && departure.isAfter(booking.getArrival()))
                        .or(booking -> arrival.isBefore(booking.getDeparture())
                                && departure.isAfter(booking.getDeparture())))
                .toList();
        return bookingList.isEmpty();
    }

    private RoomResponse addRoomDescription(RoomResponse roomResponse) {
        String roomDescription = descriptionService.getDescription(roomResponse.getRoomCategory());
        return roomResponse.setDescription(roomDescription);
    }
}