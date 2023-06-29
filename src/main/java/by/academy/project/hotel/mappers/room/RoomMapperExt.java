package by.academy.project.hotel.mappers.room;



import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategories;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.util.UUID;

public class RoomMapperExt extends RoomMapper {
    public Room buildRoom(String number, double price, RoomCategories roomCategories, RoomStatus status){
        return Room.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .number(number)
                .price(price)
                .roomCategories(roomCategories)
                .isBooked(false)
                .roomStatus(status)
                .build();
    }
}
