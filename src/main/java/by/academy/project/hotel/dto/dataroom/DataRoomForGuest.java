package by.academy.project.hotel.dto.dataroom;

import by.academy.project.hotel.entities.room.RoomCategory;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataRoomForGuest {
    private String number;
    private double price;
    private RoomCategory roomCategory;
    private Boolean isBooked;
}
