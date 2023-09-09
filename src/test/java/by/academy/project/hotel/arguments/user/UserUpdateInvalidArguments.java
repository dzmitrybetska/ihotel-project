package by.academy.project.hotel.arguments.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.user.UserInvalidArguments.USER;
import static by.academy.project.hotel.arguments.user.UserUpdateArguments.USER_REQUEST;

public class UserUpdateInvalidArguments implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(USER, USER_REQUEST)
        );
    }
}
