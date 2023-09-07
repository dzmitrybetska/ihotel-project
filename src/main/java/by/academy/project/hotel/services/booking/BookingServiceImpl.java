package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.repositories.booking.BookingRepository;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static by.academy.project.hotel.utils.Constants.BOOKING_NOT_FOUND_BY_ID;
import static by.academy.project.hotel.utils.Constants.ERROR_MESSAGE_CREATING_BOOKING;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserService userService;
    private final RoomService roomService;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public BookingResponse book(BookingRequest bookingRequest) {
        Booking booking = bookingMapper.mapToBooking(bookingRequest);
        Optional<User> optionalUser = userService.findUserByIdForBooking(bookingRequest.getUserId());
        List<Room> rooms = roomService.findRoomsByIdForBooking(bookingRequest.getIdsRooms());
        if (optionalUser.isPresent() && !rooms.isEmpty()) {
            booking.setUser(optionalUser.get()).setRooms(rooms);
            return bookingMapper.mapToBookingResponse(bookingRepository.save(booking));
        } else {
            throw new BookingNotCreatedException(ERROR_MESSAGE_CREATING_BOOKING);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> read() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(bookingMapper::mapToBookingResponse)
                .toList();
    }

    @Override
    @Transactional
    public BookingResponse update(Long id, BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking
                .map(((Function<Booking, Booking>) (booking -> bookingMapper.updateBooking(bookingRequest, booking)))
                        .andThen(booking -> changeRoomList(bookingRequest, booking)))
                .map(((Function<Booking, Booking>) (bookingRepository::save))
                        .andThen(bookingMapper::mapToBookingResponse))
                .orElseThrow(() -> new EntityNotFoundException(format(BOOKING_NOT_FOUND_BY_ID, id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse findBookingByID(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking
                .map(bookingMapper::mapToBookingResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(BOOKING_NOT_FOUND_BY_ID, id)));
    }

    private Booking changeRoomList(BookingRequest bookingRequest, Booking booking) {
        List<Long> idsRooms = bookingRequest.getIdsRooms();
        List<Room> rooms = roomService.findRoomsByIdForBooking(idsRooms);
        return booking.setRooms(rooms);
    }
}
