package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String number;
    private BigDecimal price;
    private RoomCategory roomCategory;
    private Boolean isBooked;
    private RoomStatus roomStatus;
    private List<BookingResponse> bookings;
}
