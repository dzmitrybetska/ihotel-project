package by.academy.project.hotel.mappers.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;

public abstract class RoomMapper {
    public abstract Room buildRoom(String number, double price, RoomCategory roomCategory, RoomStatus status);
}
