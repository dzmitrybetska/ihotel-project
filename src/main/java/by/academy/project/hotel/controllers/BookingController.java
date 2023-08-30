package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.services.booking.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(value = "/booking")
    public BookingResponse book(@Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.book(bookingRequest);
    }

    @GetMapping(value = "/bookings")
    public List<BookingResponse> read() {
        return bookingService.read();
    }

    @PutMapping(value = "/booking/{id}")
    public BookingResponse update(@PathVariable Long id, @Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.update(id, bookingRequest);
    }

    @DeleteMapping(value = "/booking/{id}")
    public void delete(@PathVariable Long id) {
        bookingService.delete(id);
    }

    @GetMapping(value = "/booking/{id}")
    public BookingResponse findBookingByID(@PathVariable Long id) {
        return bookingService.findBookingByID(id);
    }
}
