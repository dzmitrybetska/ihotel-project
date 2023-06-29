package by.academy.project.hotel.entities.room;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Room{
    private final String id;
    private String number;
    private double price;
    private RoomCategories roomCategories;
    private Boolean isBooked;
    private RoomStatus roomStatus;

    public Room setNumber(String number) {
        this.number = number;
        return this;
    }

    public Room setPrice(double price) {
        this.price = price;
        return this;
    }

    public Room setRoomCategories(RoomCategories roomCategories) {
        this.roomCategories = roomCategories;
        return this;
    }

    public Room setBooked(boolean booked) {
        isBooked = booked;
        return this;
    }

    public Room setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
        return this;
    }
}
