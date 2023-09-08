package by.academy.project.hotel.arguments;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.Address;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static by.academy.project.hotel.entities.user.Country.BELARUS;
import static by.academy.project.hotel.entities.user.Country.POLAND;
import static by.academy.project.hotel.entities.user.Role.ADMIN;
import static by.academy.project.hotel.entities.user.Role.MANAGER;

public class UserUpdateArguments implements ArgumentsProvider {

    public static final User user = User.builder()
            .id(2L)
            .name("Iryna")
            .surname("Betska")
            .login("ira88ira")
            .password("wer432234ret")
            .passport(new Passport(2L, "TY", "6783429", BELARUS))
            .email("iryna.betska@gmail.com")
            .phone("234567623435")
            .role(MANAGER)
            .build();

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

    public static final UserResponse userResponse = UserResponse.builder()
            .id(2L)
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
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("43-234", POLAND, "Torun", "Dluga", "12", "45"));
        user.setAddresses(addresses);
        List<Address> addressesForUpdate = new ArrayList<>();
        addressesForUpdate.add(new Address("43-234", POLAND, "Warszawa", "Krotka", "23", "18"));
        userRequest.setAddresses(addressesForUpdate);
        userResponse.setAddresses(addressesForUpdate);
        return Stream.of(
                Arguments.of(userRequest, user, userResponse)
        );
    }
}
