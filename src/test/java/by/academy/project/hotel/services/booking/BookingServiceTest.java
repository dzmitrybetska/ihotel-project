package by.academy.project.hotel.services.booking;

import by.academy.project.hotel.arguments.booking.BookingBookArguments;
import by.academy.project.hotel.arguments.booking.BookingGetArguments;
import by.academy.project.hotel.arguments.booking.BookingInvalidArguments;
import by.academy.project.hotel.arguments.booking.BookingUpdateArguments;
import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.repositories.booking.BookingRepository;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing methods of the BookingService")
public class BookingServiceTest {

    private BookingService bookingService;
    @Mock
    private UserService userService;
    @Mock
    private RoomService roomService;
    @Autowired
    private BookingMapper bookingMapper;
    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void init() {
        bookingService = new BookingServiceImpl(userService, roomService, bookingMapper, bookingRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingBookArguments.class)
    void bookTest(BookingRequest bookingRequest, Booking booking, BookingResponse bookingResponse,
                  User user, Room room) {
        when(userService.findUserByIdForBooking(any(Long.class))).thenReturn(Optional.of(user));
        when(roomService.findRoomsByIdForBooking(anyList())).thenReturn(List.of(room));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        BookingResponse actualBookingResponse = bookingService.book(bookingRequest);
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingGetArguments.class)
    void readBookingsTest(Booking booking, BookingResponse bookingResponse) {
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        List<BookingResponse> bookingResponseList = bookingService.read();
        assertEquals(Collections.singletonList(bookingResponse), bookingResponseList);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingUpdateArguments.class)
    void updateBookingTest(BookingRequest bookingRequest, Booking booking, BookingResponse bookingResponse, Room newRoom) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(roomService.findRoomsByIdForBooking(anyList())).thenReturn(List.of(newRoom));
        BookingResponse actualBookingResponse = bookingService.update(booking.getId(), bookingRequest);
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingGetArguments.class)
    void findBookingById(Booking booking, BookingResponse bookingResponse) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.of(booking));
        BookingResponse actualBookingResponse = bookingService.findBookingById(booking.getId());
        assertEquals(bookingResponse, actualBookingResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(BookingInvalidArguments.class)
    void findBookingByIdExpectedException(Long id) {
        when(bookingRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookingService.findBookingById(id));
    }
}
