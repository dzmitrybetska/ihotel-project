package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RoomRequest {

    private String number;
    private BigDecimal price;
    private RoomCategory roomCategory;
    private Boolean isBooked;
    private RoomStatus roomStatus;
}
