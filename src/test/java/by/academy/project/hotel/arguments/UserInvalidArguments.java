package by.academy.project.hotel.arguments;

import by.academy.project.hotel.entities.user.Address;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

import static by.academy.project.hotel.entities.user.Country.BELARUS;
import static by.academy.project.hotel.entities.user.Country.POLAND;
import static by.academy.project.hotel.entities.user.Role.GUEST;

public class UserInvalidArguments implements ArgumentsProvider {

    public static final User user = User.builder()
            .id(25L)
            .name("Eva")
            .surname("Betska")
            .login("baxset")
            .password("tyrew123werty")
            .passport(new Passport(25L, "AV", "2343456", BELARUS))
            .email("eva.betska@gmail.com")
            .phone("23456765433")
            .addresses(List.of(new Address("43-234", POLAND, "Poznan", "Chrabrego", "23", "46")))
            .role(GUEST)
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(user)
        );
    }
}
