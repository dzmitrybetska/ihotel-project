package by.academy.project.hotel.dto.dataroom;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataRoomForManager {
    private String number;
    private double price;
    private RoomCategory roomCategory;
    private Boolean isBooked;
    private RoomStatus roomStatus;
}
