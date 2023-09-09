package by.academy.project.hotel.arguments.booking;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.booking.BookingBookArguments.BOOKING;
import static by.academy.project.hotel.arguments.booking.BookingBookArguments.BOOKING_RESPONSE;

public class BookingGetArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(BOOKING, BOOKING_RESPONSE)
        );
    }
}
