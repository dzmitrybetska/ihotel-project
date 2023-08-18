package by.academy.project.hotel.mappers;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static by.academy.project.hotel.util.configuration.Constants.*;

public final class UserMapper {

    private static UserMapper instance;
    private final BookingMapper bookingMapper = BookingMapper.getInstance();

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    public User buildUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname((userDto.getSurname()))
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .passport(userDto.getPassport())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(userDto.getRole())
                .addresses(userDto.getAddresses())
                .build();
    }

    public void updateUser(User user, UserDto userDto) {
        user.setName(userDto.getName())
                .setSurname(userDto.getSurname())
                .setPassword(userDto.getPassword())
                .setEmail(userDto.getEmail())
                .setPhone(userDto.getPhone())
                .setRole(userDto.getRole())
                .setAddresses(userDto.getAddresses())
                .getPassport()
                .setPassportSeries(userDto.getPassport().getPassportSeries())
                .setPassportID(userDto.getPassport().getPassportID())
                .setCountry(userDto.getPassport().getCountry());
    }

    public Passport buildPassport(String passportSeries, String passportID, Country country) {
        return Passport.builder()
                .passportSeries(passportSeries)
                .passportID(passportID)
                .country(country)
                .build();
    }

    public UserDto buildUserDtoForGuest(UserDto userDto) {
        return UserDto.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(userDto.getRole())
                .build();
    }

    public UserDto buildUserDtoForManager(UserDto userDto) {
        return UserDto.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .passport(userDto.getPassport())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(userDto.getRole())
                .build();
    }

    public UserDto buildUserDto(User user) {
        return user.getBookings() != null ? buildUserDtoWithBookings(user) : buildUserDtoWithoutBookings(user);
    }

    public UserDto buildUserDtoForGuest(HttpServletRequest req) {
        return UserDto.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .passport(new Passport())
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.GUEST)
                .build();
    }

    public UserDto buildUserDtoForManager(HttpServletRequest req) {
        return UserDto.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .passport(this.buildPassport(
                        req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase()))
                )
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.GUEST)
                .build();
    }

    public UserDto buildUserDto(HttpServletRequest req) {
        return UserDto.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .passport(this.buildPassport(
                        req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase()))
                )
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.valueOf(req.getParameter(ROLE).toUpperCase()))
                .build();
    }

    public UserDto buildUserDtoWithoutBookings(User user) {
        return UserDto.builder()
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

    public List<UserDto> filterUsersDto(List<UserDto> users) {
        return users.stream()
                .filter(user -> user.getRole() == Role.GUEST)
                .map(this::buildUserDtoForManager)
                .collect(Collectors.toList());
    }

    public List<UserDto> buildUsersDto(List<User> users) {
        return users.stream()
                .map(this::buildUserDto)
                .collect(Collectors.toList());
    }

    private UserDto buildUserDtoWithBookings(User user) {
        return UserDto.builder()
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
                .bookings(bookingMapper.buildBookingsDtoForUser(user.getBookings()))
                .build();
    }
}

