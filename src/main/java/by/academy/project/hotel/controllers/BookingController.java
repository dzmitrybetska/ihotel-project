package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.services.booking.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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
    public BookingResponse update(@PathVariable @Min(1) Long id, @Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.update(id, bookingRequest);
    }

    @DeleteMapping(value = "/booking/{id}")
    public boolean delete(@PathVariable Long id) {
        return bookingService.delete(id);
    }
}
