package by.academy.project.hotel.arguments.booking;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER;
import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER_RESPONSE;
import static by.academy.project.hotel.entities.booking.Rate.FLEXIBLE;
import static by.academy.project.hotel.entities.room.RoomCategory.STANDART;
import static by.academy.project.hotel.entities.room.RoomStatus.SERVICED;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;

public class BookingBookArguments implements ArgumentsProvider {

    public static final BookingRequest BOOKING_REQUEST = BookingRequest.builder()
            .userId(1L)
            .idsRooms(List.of(1L))
            .rate(FLEXIBLE)
            .arrival(now())
            .departure(now())
            .build();

    public static final Room ROOM = Room.builder()
            .id(1L)
            .number("1")
            .price(valueOf(125))
            .roomCategory(STANDART)
            .isBooked(true)
            .roomStatus(SERVICED)
            .build();

    public static final Booking BOOKING = Booking.builder()
            .id(1L)
            .user(USER)
            .rooms(List.of(ROOM))
            .rate(FLEXIBLE)
            .arrival(now())
            .departure(now())
            .build();

    public static final RoomResponse ROOM_RESPONSE = RoomResponse.builder()
            .id(1L)
            .number("1")
            .price(valueOf(125))
            .roomCategory(STANDART)
            .isBooked(true)
            .roomStatus(SERVICED)
            .build();

    public static final BookingResponse BOOKING_RESPONSE = BookingResponse.builder()
            .id(1L)
            .userResponse(USER_RESPONSE)
            .rooms(List.of(ROOM_RESPONSE))
            .rate(FLEXIBLE)
            .arrival(now())
            .departure(now())
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(BOOKING_REQUEST, BOOKING, BOOKING_RESPONSE, USER, ROOM)
        );
    }
}
