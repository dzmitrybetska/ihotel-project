package by.academy.project.hotel.arguments.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER;
import static by.academy.project.hotel.arguments.user.UserCreateArguments.USER_RESPONSE;

public class UserGetArguments implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(USER, USER_RESPONSE)
        );
    }
}
