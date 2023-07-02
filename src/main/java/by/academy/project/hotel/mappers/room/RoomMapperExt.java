package by.academy.project.hotel.mappers.room;


import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;

import java.util.UUID;

public final class RoomMapperExt extends RoomMapper {
    private static RoomMapperExt instance;
    private RoomMapperExt(){}

    public static RoomMapperExt getInstance() {
        if (instance == null){
            instance = new RoomMapperExt();
        }
        return instance;
    }

    public Room buildRoom(String number, double price, RoomCategory roomCategory, RoomStatus status){
        return Room.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .number(number)
                .price(price)
                .roomCategory(roomCategory)
                .isBooked(false)
                .roomStatus(status)
                .build();
    }
}
