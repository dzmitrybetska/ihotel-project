package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.dto.BookingRequest;
import by.academy.project.hotel.dto.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.repositories.booking.BookingRepository;
import by.academy.project.hotel.repositories.room.RoomRepository;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_CREATING_BOOKING;
import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_SEARCHING_BOOKING;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingResponse create(BookingRequest bookingRequest, Long userId, List<Long> roomsId) {
        Booking booking = bookingMapper.buildBooking(bookingRequest);
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Room> rooms = roomRepository.findAllById(roomsId);
        if (optionalUser.isPresent() && rooms.size() != 0) {
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
                .map(bookingMapper::buildBookingResponse)
                .orElseThrow(()-> new EntityNotFoundException(ERROR_MESSAGE_SEARCHING_BOOKING));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        optionalBooking.ifPresent(bookingRepository::delete);
        return optionalBooking.isPresent();
    }
}
