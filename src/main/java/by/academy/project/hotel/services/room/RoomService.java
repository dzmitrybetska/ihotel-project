package by.academy.project.hotel.services.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategories;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room createRoom(Room room);
    List<Room> readRooms();
    Room updateRoom(String id, Room room);
    Room updateRoomStatus(String id, RoomStatus status);
    Room deleteRoom(String id);
    Optional<Room> getRoomByRoomID(String id);
    Optional<Room> findRoomByNumber(String number);
    List<Room> roomSearchByCategory(RoomCategories category);


}
