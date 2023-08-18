package by.academy.project.hotel.services.room;


import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.exceptions.RoomNotAddedException;
import by.academy.project.hotel.mappers.RoomMapper;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.repositories.room.RoomRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_ADDING_ROOM;
import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_BY_ROOM;


public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper = RoomMapper.getInstance();

    public RoomServiceImpl() {
        roomRepository = RoomRepositoryImpl.getInstance();
    }

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public RoomDto add(RoomDto room) {
        Optional<Room> optionalRoom = roomRepository.add(room);
        return optionalRoom.map(roomMapper::buildRoomDto).orElseThrow(() -> new RoomNotAddedException(ERROR_MESSAGE_ADDING_ROOM));
    }

    @Override
    public List<RoomDto> read() {
        return roomMapper.buildRoomsDto(roomRepository.read());
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.update(roomDto);
        return optionalRoom.map(roomMapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Room> optionalRoom = roomRepository.delete(id);
        return optionalRoom.isPresent();
    }

    @Override
    public RoomDto getByID(Long id) {
        Optional<Room> optionalRoom = roomRepository.getByID(id);
        return optionalRoom.map(roomMapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public RoomDto getRoomByNumber(String number) {
        Optional<Room> optionalRoom = roomRepository.getRoomByNumber(number);
        return optionalRoom.map(roomMapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public List<RoomDto> searchRoomsByCategory(RoomCategory category) {
        List<Room> rooms = roomRepository.searchRoomsByCategory(category);
        if (rooms.size() != 0) {
            return roomMapper.buildRoomsDto(rooms);
        } else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }
}