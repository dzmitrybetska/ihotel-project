package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.BookingRequest;
import by.academy.project.hotel.dto.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse create(BookingRequest bookingRequest, Long userId, List<Long> roomsId);

    List<BookingResponse> read();

    BookingResponse update(Long id, BookingRequest bookingRequest);

    boolean delete(Long id);
}
