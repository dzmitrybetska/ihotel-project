package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingDto bookingDto, Long userId, List<Long> roomsId);

    List<BookingDto> read();

    BookingDto update(BookingDto bookingDto);

    boolean delete(Long id);
}
