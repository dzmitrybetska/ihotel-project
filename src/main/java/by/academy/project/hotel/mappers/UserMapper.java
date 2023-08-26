package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class UserMapper {

    public User buildUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname((userRequest.getSurname()))
                .login(userRequest.getLogin())
                .password(userRequest.getPassword())
                .passport(userRequest.getPassport())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .role(userRequest.getRole())
                .addresses(userRequest.getAddresses())
                .build();
    }

    public User updateUser(User user, UserRequest userRequest) {
        user.setName(userRequest.getName())
                .setSurname(userRequest.getSurname())
                .setPassword(userRequest.getPassword())
                .setEmail(userRequest.getEmail())
                .setPhone(userRequest.getPhone())
                .setRole(userRequest.getRole())
                .setAddresses(userRequest.getAddresses())
                .getPassport()
                .setPassportSeries(userRequest.getPassport().getPassportSeries())
                .setPassportID(userRequest.getPassport().getPassportID())
                .setCountry(userRequest.getPassport().getCountry());
        return user;
    }

    public UserResponse buildUserDto(User user, BookingMapper bookingMapper) {
        return user.getBookings() != null ? buildUserDtoWithBookings(user, bookingMapper) : buildUserDtoWithoutBookings(user);
    }

    public UserResponse buildUserDtoWithoutBookings(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .addresses(user.getAddresses())
                .build();
    }

    public List<UserResponse> buildUsersDto(List<User> users, BookingMapper bookingMapper) {
        return users.stream()
                .map(user -> buildUserDto(user, bookingMapper))
                .toList();
    }

    private UserResponse buildUserDtoWithBookings(User user, BookingMapper bookingMapper) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .addresses(user.getAddresses())
                .bookings(bookingMapper.buildBookingsResponseForUser(user.getBookings()))
                .build();
    }
}

