package by.academy.project.hotel.repositories.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room createRoom(Room room);
    List<Room> readRooms();
    Optional<Room> updateRoom(String id, Room room);
    Optional<Room> updateRoomStatus(String id, RoomStatus status);
    Optional<Room> deleteRoom(String id);
    Optional<Room> getRoomByRoomID(String id);
    Optional<Room> findRoomByNumber(String number);
    List<Room> roomSearchByCategory(RoomCategory category);
}
