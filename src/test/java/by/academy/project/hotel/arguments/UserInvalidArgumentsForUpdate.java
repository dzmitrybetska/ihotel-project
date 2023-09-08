package by.academy.project.hotel.arguments;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.entities.user.Passport;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static by.academy.project.hotel.entities.user.Country.BELARUS;
import static by.academy.project.hotel.entities.user.Role.ADMIN;

public class UserInvalidArgumentsForUpdate implements ArgumentsProvider {

    public static final Long id = 24L;
    public static final UserRequest userRequest = UserRequest.builder()
            .name("Eva")
            .surname("Betska")
            .login("eVa2016")
            .password("435635183e")
            .passport(new Passport(2L, "QW", "1783425", BELARUS))
            .email("iryna.betska@gmail.com")
            .phone("34568923212")
            .role(ADMIN)
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(id, userRequest)
        );
    }
}
