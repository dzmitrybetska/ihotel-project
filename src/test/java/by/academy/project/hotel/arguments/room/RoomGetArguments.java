package by.academy.project.hotel.arguments.room;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.room.RoomAddArguments.ROOM;
import static by.academy.project.hotel.arguments.room.RoomAddArguments.ROOM_RESPONSE;

public class RoomGetArguments implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(ROOM, ROOM_RESPONSE)
        );
    }
}
