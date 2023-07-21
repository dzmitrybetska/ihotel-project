package by.academy.project.hotel.services.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.repositories.room.RoomRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_BY_ROOM;

public final class RoomServiceImpl implements RoomService{
    private static RoomServiceImpl instance;
    private final RoomRepository roomRepository;

    private RoomServiceImpl(){
        roomRepository = new RoomRepositoryImpl();
    }

    public static RoomServiceImpl getInstance(){
        if (instance == null){
            instance = new RoomServiceImpl();
        }
        return instance;
    }
    @Override
    public Room createRoom(Room room) {
        roomRepository.createRoom(room);
        return room;
    }

    @Override
    public List<Room> readRooms() {
        return roomRepository.readRooms();
    }

    @Override
    public Room updateRoom(String id, Room room) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.updateRoom(id, room);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public Room updateRoomStatus(String id, RoomStatus status) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.updateRoomStatus(id, status);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public Room deleteRoom(String id) throws NotFoundRoomException {
        Optional<Room> optional = roomRepository.deleteRoom(id);
        if (optional.isPresent()){
            return optional.get();
        }else{
            throw new NotFoundRoomException(ERROR_MESSAGE_BY_ROOM);
        }
    }

    @Override
    public Optional<Room> getRoomByRoomID(String id) {
        return roomRepository.getRoomByRoomID(id);
    }

    @Override
    public Optional<Room> findRoomByNumber(String number){
        return roomRepository.findRoomByNumber(number);
    }

    @Override
    public List<Room> roomSearchByCategory(RoomCategory category) {
        return roomRepository.roomSearchByCategory(category);
    }
}
