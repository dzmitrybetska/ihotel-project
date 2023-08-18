package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.BookingDto;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import by.academy.project.hotel.exceptions.NotFoundBookingException;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.repositories.booking.BookingRepository;
import by.academy.project.hotel.repositories.booking.BookingRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_CREATING_BOOKING;
import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_SEARCHING_BOOKING;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository = BookingRepositoryImpl.getInstance();
    private final BookingMapper bookingMapper = BookingMapper.getInstance();

    @Override
    public BookingDto create(BookingDto bookingDto, Long userId, List<Long> roomsId) {
        Optional<Booking> optionalBooking = bookingRepository.add(bookingDto, userId, roomsId);
        return optionalBooking.map(bookingMapper::buildBookingDto).orElseThrow(() -> new BookingNotCreatedException(ERROR_MESSAGE_CREATING_BOOKING));
    }

    @Override
    public List<BookingDto> read() {
        return bookingMapper.buildBookingsDto(bookingRepository.read());
    }

    @Override
    public BookingDto update(BookingDto bookingDto) throws NotFoundBookingException {
        Optional<Booking> optionalBooking = bookingRepository.update(bookingDto);
        return optionalBooking.map(bookingMapper::buildBookingDto).orElseThrow(() -> new NotFoundBookingException(ERROR_MESSAGE_SEARCHING_BOOKING));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.delete(id);
        return optionalBooking.isPresent();
    }
}
