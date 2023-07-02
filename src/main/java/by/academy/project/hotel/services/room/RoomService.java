package by.academy.project.hotel.services.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.NotFoundRoomException;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room createRoom(Room room);
    List<Room> readRooms();
    Room updateRoom(String id, Room room) throws NotFoundRoomException;
    Room updateRoomStatus(String id, RoomStatus status) throws NotFoundRoomException;
    Room deleteRoom(String id) throws NotFoundRoomException;
    Optional<Room> getRoomByRoomID(String id);
    Optional<Room> findRoomByNumber(String number);
    List<Room> roomSearchByCategory(RoomCategory category);
}
