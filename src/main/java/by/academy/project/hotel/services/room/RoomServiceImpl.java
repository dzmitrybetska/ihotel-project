package by.academy.project.hotel.services.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategories;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.repositories.room.RoomRepositoryImpl;

import java.util.List;
import java.util.Optional;

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
    public Room updateRoom(String id, Room room) {
        return roomRepository.updateRoom(id, room);
    }

    @Override
    public Room updateRoomStatus(String id, RoomStatus status){
        return roomRepository.updateRoomStatus(id, status);
    }

    @Override
    public Room deleteRoom(String id) {
       return roomRepository.deleteRoom(id);
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
    public List<Room> roomSearchByCategory(RoomCategories category) {
        return roomRepository.roomSearchByCategory(category);
    }

}
