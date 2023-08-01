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
    private final RoomMapper mapper = RoomMapper.getInstance();

    public RoomServiceImpl() {
        roomRepository = RoomRepositoryImpl.getInstance();
    }

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public RoomDto add(RoomDto room) throws RoomNotAddedException {
        Optional<Room> optional = roomRepository.add(room);
        return optional.map(mapper::buildRoomDto).orElseThrow(() -> new RoomNotAddedException(ERROR_MESSAGE_ADDING_ROOM));
    }

    @Override
    public List<RoomDto> read() {
        return mapper.buildRoomsDto(roomRepository.read());
    }

    @Override
    public RoomDto update(RoomDto roomDto) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.update(roomDto);
        return optional.map(mapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Room> optional = roomRepository.delete(id);
        return optional.isPresent();
    }

    @Override
    public RoomDto getByID(Long id) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.getByID(id);
        return optional.map(mapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public RoomDto getRoomByNumber(String number) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.getRoomByNumber(number);
        return optional.map(mapper::buildRoomDto).orElseThrow(() -> new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM));
    }

    @Override
    public List<RoomDto> searchRoomsByCategory(RoomCategory category) throws NotFoundRoomException {
        List<Room> rooms = roomRepository.searchRoomsByCategory(category);
        if (rooms.size() != 0) {
            return mapper.buildRoomsDto(rooms);
        } else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }
}