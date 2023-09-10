package by.academy.project.hotel.dto.responces;

import by.academy.project.hotel.entities.booking.Rate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    @JsonInclude(NON_NULL)
    private UserResponse userResponse;
    @JsonInclude(NON_EMPTY)
    private List<RoomResponse> rooms;
    private Rate rate;
    private LocalDate arrival;
    private LocalDate departure;
}
