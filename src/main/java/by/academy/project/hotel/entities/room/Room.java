package by.academy.project.hotel.entities.room;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Room{
    private final String id;
    private String number;
    private double price;
    private RoomCategory roomCategory;
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

    public Room setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory= roomCategory;
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
