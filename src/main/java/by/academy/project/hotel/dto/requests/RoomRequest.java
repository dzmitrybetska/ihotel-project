package by.academy.project.hotel.dto.requests;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RoomRequest {

    @NotBlank
    private String number;
    @NotNull
    private BigDecimal price;
    @NotNull
    private RoomCategory roomCategory;
    @NotNull
    private Boolean isBooked;
    @NotNull
    private RoomStatus roomStatus;
}
