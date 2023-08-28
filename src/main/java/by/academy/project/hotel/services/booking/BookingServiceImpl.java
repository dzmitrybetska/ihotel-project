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

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.utils.Constants.ERROR_MESSAGE_CREATING_BOOKING;
import static by.academy.project.hotel.utils.Constants.ERROR_MESSAGE_SEARCHING_BOOKING;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public BookingResponse create(BookingRequest bookingRequest) {
        Booking booking = bookingMapper.buildBooking(bookingRequest);
        Optional<User> optionalUser = userService.findUserByIDForBooking(bookingRequest.getUserId());
        List<Room> rooms = roomService.findRoomsByIdForBooking(bookingRequest.getIdsRooms());
        if (optionalUser.isPresent() && !rooms.isEmpty()) {
            booking.setUser(optionalUser.get())
                    .setRooms(rooms);
            return bookingMapper.buildBookingResponse(bookingRepository.save(booking));
        } else throw new BookingNotCreatedException(ERROR_MESSAGE_CREATING_BOOKING);
    }

    @Override
    public List<BookingResponse> read() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.buildBookingsResponse(bookings);
    }

    @Override
    public BookingResponse update(Long id, BookingRequest bookingRequest) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking.map(booking -> bookingMapper.updateBooking(booking, bookingRequest))
                .map(booking -> booking.setRooms(roomService.findRoomsByIdForBooking(bookingRequest.getIdsRooms())))
                .map(bookingRepository::save)
                .map(bookingMapper::buildBookingResponse)
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_SEARCHING_BOOKING + id));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        optionalBooking.ifPresent(bookingRepository::delete);
        return optionalBooking.isPresent();
    }

    @Override
    public BookingResponse findBookingByID(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking.map(bookingMapper::buildBookingResponse)
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_SEARCHING_BOOKING + id));
    }
}
