package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.BookingDto;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import by.academy.project.hotel.exceptions.NotFoundBookingException;

import java.util.List;

public interface BookingService {
    BookingDto create(BookingDto bookingDto, Long userId, List<Long> roomsId) throws BookingNotCreatedException;

    List<BookingDto> read();

    BookingDto update(BookingDto bookingDto) throws NotFoundBookingException;

    boolean delete(Long id);
}
