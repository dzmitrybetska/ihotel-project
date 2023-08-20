package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.BookingResponse;
import by.academy.project.hotel.services.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "/bookings")
    public List<BookingResponse> read(){
        return bookingService.read();
    }
}
