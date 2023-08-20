package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.UserRequest;
import by.academy.project.hotel.dto.UserResponse;
import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public Passport buildPassport(String passportSeries, String passportID, Country country) {
        return Passport.builder()
                .passportSeries(passportSeries)
                .passportID(passportID)
                .country(country)
                .build();
    }

    public UserResponse buildUserDtoForGuest(UserResponse userResponse) {
        return UserResponse.builder()
                .id(userResponse.getId())
                .name(userResponse.getName())
                .surname(userResponse.getSurname())
                .login(userResponse.getLogin())
                .password(userResponse.getPassword())
                .email(userResponse.getEmail())
                .phone(userResponse.getPhone())
                .role(userResponse.getRole())
                .build();
    }

    public UserResponse buildUserDtoForManager(UserResponse userResponse) {
        return UserResponse.builder()
                .id(userResponse.getId())
                .name(userResponse.getName())
                .surname(userResponse.getSurname())
                .passport(userResponse.getPassport())
                .email(userResponse.getEmail())
                .phone(userResponse.getPhone())
                .role(userResponse.getRole())
                .build();
    }

    public UserResponse buildUserDto(User user, BookingMapper bookingMapper) {
        return user.getBookings() != null ? buildUserDtoWithBookings(user, bookingMapper) : buildUserDtoWithoutBookings(user);
    }

//    public UserDto buildUserDtoForGuest(HttpServletRequest req) {
//        return UserDto.builder()
//                .name(req.getParameter(NAME))
//                .surname(req.getParameter(SURNAME))
//                .login(req.getParameter(LOGIN))
//                .password(req.getParameter(PASSWORD))
//                .passport(new Passport())
//                .email(req.getParameter(EMAIL))
//                .phone(req.getParameter(PHONE))
//                .role(Role.GUEST)
//                .build();
//    }
//
//    public UserDto buildUserDtoForManager(HttpServletRequest req) {
//        return UserDto.builder()
//                .name(req.getParameter(NAME))
//                .surname(req.getParameter(SURNAME))
//                .passport(this.buildPassport(
//                        req.getParameter(PASSPORT_SERIES),
//                        req.getParameter(PASSPORT_ID),
//                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase()))
//                )
//                .email(req.getParameter(EMAIL))
//                .phone(req.getParameter(PHONE))
//                .role(Role.GUEST)
//                .build();
//    }
//
//    public UserDto buildUserDto(HttpServletRequest req) {
//        return UserDto.builder()
//                .name(req.getParameter(NAME))
//                .surname(req.getParameter(SURNAME))
//                .login(req.getParameter(LOGIN))
//                .password(req.getParameter(PASSWORD))
//                .passport(this.buildPassport(
//                        req.getParameter(PASSPORT_SERIES),
//                        req.getParameter(PASSPORT_ID),
//                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase()))
//                )
//                .email(req.getParameter(EMAIL))
//                .phone(req.getParameter(PHONE))
//                .role(Role.valueOf(req.getParameter(ROLE).toUpperCase()))
//                .build();
//    }

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

    public List<UserResponse> filterUsersDto(List<UserResponse> users) {
        return users.stream()
                .filter(user -> user.getRole() == Role.GUEST)
                .map(this::buildUserDtoForManager)
                .collect(Collectors.toList());
    }

    public List<UserResponse> buildUsersDto(List<User> users, BookingMapper bookingMapper) {
        return users.stream()
                .map(user -> buildUserDto(user, bookingMapper))
                .collect(Collectors.toList());
    }

    private UserResponse buildUserDtoWithBookings(User user, BookingMapper bookingMapper){
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

