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


public final class RoomServiceImpl implements RoomService {
    private static RoomServiceImpl instance;
    private final RoomRepository roomRepository;
    private final RoomMapper mapper = RoomMapper.getInstance();

    private RoomServiceImpl() {
        roomRepository = new RoomRepositoryImpl();
    }

    public static RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    @Override
    public RoomDto add(RoomDto room) throws RoomNotAddedException {
        Optional<Room> optional = roomRepository.add(room);
        if (optional.isPresent()) {
            return mapper.buildRoomDto(optional.get());
        } else {
            throw new RoomNotAddedException(ERROR_MESSAGE_ADDING_ROOM);
        }
    }

    @Override
    public List<RoomDto> read() {
        return mapper.buildRoomsDto(roomRepository.read());
    }

    @Override
    public RoomDto update(RoomDto roomDto) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.update(roomDto);
        if (optional.isPresent()) {
            return mapper.buildRoomDto(optional.get());
        } else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Room> optional = roomRepository.delete(id);
        return optional.isPresent();
    }

    @Override
    public RoomDto getByID(Long id) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.getByID(id);
        if (optional.isPresent()) {
            return mapper.buildRoomDto(optional.get());
        } else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public RoomDto getRoomByNumber(String number) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.getRoomByNumber(number);
        if (optional.isPresent()) {
            return mapper.buildRoomDto(optional.get());
        } else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public List<RoomDto> searchRoomsByCategory(RoomCategory category) {
        return mapper.buildRoomsDto(roomRepository.searchRoomsByCategory(category));
    }
}