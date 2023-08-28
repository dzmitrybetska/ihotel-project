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
    @Size(min = 1, max = 3)
    @NotEmpty
    private List<Long> idsRooms;
    @NotNull
    private Rate rate;
    @FutureOrPresent
    @NotNull
    private LocalDate arrival;
    @Future
    @NotNull
    private LocalDate departure;
}
