package by.academy.project.hotel.arguments.user;

import by.academy.project.hotel.entities.user.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserInvalidArguments implements ArgumentsProvider {

    public static final User USER = User.builder()
            .id(25L)
            .name("Eva")
            .surname("Betska")
            .login("baxset")
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(USER)
        );
    }
}
