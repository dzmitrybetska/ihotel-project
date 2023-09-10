package by.academy.project.hotel.dto.responces;

import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String number;
    private BigDecimal price;
    private RoomCategory roomCategory;
    private Boolean isBooked;
    private RoomStatus roomStatus;
    @JsonInclude(NON_EMPTY)
    private List<BookingResponse> bookings;
    @JsonInclude(NON_NULL)
    private String description;
}
