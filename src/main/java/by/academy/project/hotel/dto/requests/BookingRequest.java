package by.academy.project.hotel.dto.requests;

import by.academy.project.hotel.entities.booking.Rate;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BookingRequest {

    @NotNull
    private Long userId;
    @NotEmpty
    @Size(min = 1, max = 3)
    private List<Long> idsRooms;
    @NotNull
    private Rate rate;
    @NotNull
    @FutureOrPresent
    private LocalDate arrival;
    @NotNull
    @Future
    private LocalDate departure;
}
