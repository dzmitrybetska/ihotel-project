package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse book(BookingRequest bookingRequest);

    List<BookingResponse> read();

    BookingResponse update(Long id, BookingRequest bookingRequest);

    boolean delete(Long id);

    BookingResponse findBookingByID(Long id);
}
