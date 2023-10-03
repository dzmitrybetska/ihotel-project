package by.academy.project.hotel.controllers;

import by.academy.project.hotel.aspects.SkipLogging;
import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.services.booking.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Operations on bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(value = "/booking")
    @Operation(description = "Add a new booking")
    public BookingResponse book(@Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.book(bookingRequest);
    }

    @SkipLogging
    @GetMapping(value = "/bookings")
    @Operation(description = "Get all bookings")
    public List<BookingResponse> read() {
        return bookingService.read();
    }

    @PutMapping(value = "/booking/{id}")
    @Operation(description = "Update booking details by ID")
    public BookingResponse update(@PathVariable @NotNull Long id, @Valid @RequestBody BookingRequest bookingRequest) {
        return bookingService.update(id, bookingRequest);
    }

    @DeleteMapping(value = "/booking/{id}")
    @Operation(description = "Delete a booking by ID")
    public void delete(@PathVariable @NotNull Long id) {
        bookingService.delete(id);
    }

    @GetMapping(value = "/booking/{id}")
    @Operation(description = "Find a booking by ID")
    public BookingResponse findBookingByID(@PathVariable @NotNull Long id) {
        return bookingService.findBookingById(id);
    }
}
