package by.academy.project.hotel.repositories.booking;

import by.academy.project.hotel.dto.BookingDto;
import by.academy.project.hotel.entities.booking.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional<Booking> add(BookingDto bookingDto, Long userId, List<Long> roomsId);

    List<Booking> read();

    Optional<Booking> update(BookingDto bookingDto);

    Optional<Booking> delete(Long id);
}
