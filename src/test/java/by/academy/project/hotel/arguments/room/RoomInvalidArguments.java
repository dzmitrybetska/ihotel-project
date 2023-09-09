package by.academy.project.hotel.arguments.room;

import by.academy.project.hotel.entities.room.Room;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.entities.room.RoomCategory.DELUX;

public class RoomInvalidArguments implements ArgumentsProvider {

    public static final Room ROOM = Room.builder()
            .id(24L)
            .number("12")
            .roomCategory(DELUX)
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(ROOM)
        );
    }
}
