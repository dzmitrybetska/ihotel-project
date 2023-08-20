package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.booking.Rate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingRequest {

    private Rate rate;
    private LocalDate arrival;
    private LocalDate departure;
}
