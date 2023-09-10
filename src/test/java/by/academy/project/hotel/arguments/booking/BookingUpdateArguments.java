package by.academy.project.hotel.arguments.booking;

import by.academy.project.hotel.dto.requests.BookingRequest;
import by.academy.project.hotel.dto.responces.BookingResponse;
import by.academy.project.hotel.entities.booking.Booking;
import by.academy.project.hotel.entities.room.Room;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.room.RoomAddArguments.ROOM;
import static by.academy.project.hotel.arguments.room.RoomUpdateArguments.ROOM_RESPONSE;
import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER;
import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER_RESPONSE;
import static by.academy.project.hotel.entities.booking.Rate.ADVANCE_PURCHASE;
import static by.academy.project.hotel.entities.booking.Rate.FLEXIBLE;
import static by.academy.project.hotel.entities.room.RoomCategory.SUPERIOR;
import static by.academy.project.hotel.entities.room.RoomStatus.SERVICED;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class BookingUpdateArguments implements ArgumentsProvider {

    public static final BookingRequest BOOKING_REQUEST = BookingRequest.builder()
            .userId(1L)
            .idsRooms(List.of(2L))
            .rate(ADVANCE_PURCHASE)
            .arrival(of(2023, 9, 25))
            .departure(of(2023, 9, 30))
            .build();

    public static final Booking BOOKING = Booking.builder()
            .id(1L)
            .user(USER)
            .rooms(List.of(ROOM))
            .rate(FLEXIBLE)
            .arrival(now())
            .departure(now())
            .build();

    public static final Room NEW_ROOM = Room.builder()
            .id(2L)
            .number("2")
            .price(valueOf(199))
            .roomCategory(SUPERIOR)
            .isBooked(false)
            .roomStatus(SERVICED)
            .build();

    public static final BookingResponse BOOKING_RESPONSE = BookingResponse.builder()
            .id(1L)
            .userResponse(USER_RESPONSE)
            .rooms(List.of(ROOM_RESPONSE))
            .rate(ADVANCE_PURCHASE)
            .arrival(of(2023, 9, 25))
            .departure(of(2023, 9, 30))
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(BOOKING_REQUEST, BOOKING, BOOKING_RESPONSE, NEW_ROOM)
        );
    }
}
