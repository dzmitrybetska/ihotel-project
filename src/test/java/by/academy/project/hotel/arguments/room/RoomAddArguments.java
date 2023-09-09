package by.academy.project.hotel.arguments.room;

import by.academy.project.hotel.dto.requests.RoomRequest;
import by.academy.project.hotel.dto.responces.RoomResponse;
import by.academy.project.hotel.entities.room.Room;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.entities.room.RoomCategory.STANDART;
import static by.academy.project.hotel.entities.room.RoomStatus.SERVICED;
import static java.math.BigDecimal.valueOf;

public class RoomAddArguments implements ArgumentsProvider {

    public static final RoomRequest ROOM_REQUEST = RoomRequest.builder()
            .number("1")
            .price(valueOf(125))
            .roomCategory(STANDART)
            .isBooked(false)
            .roomStatus(SERVICED)
            .build();

    public static final Room ROOM = Room.builder()
            .id(1L)
            .number("1")
            .price(valueOf(125))
            .roomCategory(STANDART)
            .isBooked(false)
            .roomStatus(SERVICED)
            .build();

    public static final RoomResponse ROOM_RESPONSE = RoomResponse.builder()
            .id(1L)
            .number("1")
            .price(valueOf(125))
            .roomCategory(STANDART)
            .isBooked(false)
            .roomStatus(SERVICED)
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(ROOM_REQUEST, ROOM, ROOM_RESPONSE)
        );
    }
}
