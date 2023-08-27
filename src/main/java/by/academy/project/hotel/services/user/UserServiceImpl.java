package by.academy.project.hotel.services.user;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_BY_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookingMapper bookingMapper;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.buildUser(userRequest);
        userRepository.save(user);
        return userMapper.buildUserDto(user, bookingMapper);
    }

    @Override
    public List<UserResponse> read() {
        List<User> users = userRepository.findAll();
        return userMapper.buildUsersDto(users, bookingMapper);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> userMapper.updateUser(user, userRequest))
                .map(userRepository::save)
                .map(user -> userMapper.buildUserDto(user, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_USER + id));
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(userRepository::delete);
        return optionalUser.isPresent();
    }

    @Override
    public UserResponse findUserByID(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> userMapper.buildUserDto(user, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_USER + id));
    }

    @Override
    public Optional<User> findUserByIDForBooking(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserResponse findUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        return optionalUser.map(user -> userMapper.buildUserDto(user, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_USER + login));
    }

    @Override
    public List<UserResponse> findUsersByNameAndSurname(String name, String surname) {
        List<User> users = userRepository.findUsersByNameAndSurname(name, surname);
        if (!users.isEmpty()) {
            return userMapper.buildUsersDto(users, bookingMapper);
        } else {
            throw new EntityNotFoundException(ERROR_MESSAGE_BY_USER + name + " " + surname);
        }
    }
}