package by.academy.project.hotel.services.user;

import by.academy.project.hotel.dto.UserRequest;
import by.academy.project.hotel.dto.UserResponse;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.exceptions.UserNotCreatedException;
import by.academy.project.hotel.mappers.BookingMapper;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.Constants.ERROR_MESSAGE_BY_USER;
import static by.academy.project.hotel.util.Constants.USER_CREATION_ERROR_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookingMapper bookingMapper;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.buildUser(userRequest);
        Optional<User> optionalUser = Optional.of(userRepository.save(user));
        return optionalUser.map(user1 -> userMapper.buildUserDto(user1, bookingMapper))
                .orElseThrow(() -> new UserNotCreatedException(USER_CREATION_ERROR_MESSAGE));
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
                .map(user -> userMapper.buildUserDto(user, bookingMapper))
                .orElseThrow(() -> new NotFoundUserException(ERROR_MESSAGE_BY_USER));
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
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_USER));
    }

    @Override
    public UserResponse findUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        return optionalUser.map(user -> userMapper.buildUserDto(user, bookingMapper))
                .orElseThrow(() -> new EntityNotFoundException(ERROR_MESSAGE_BY_USER));
    }

    @Override
    public List<UserResponse> findUsersByNameAndSurname(String name, String surname) {
        List<User> users = userRepository.findUsersByNameAndSurname(name, surname);
        if (users.size() != 0) {
            return userMapper.buildUsersDto(users, bookingMapper);
        } else {
            throw new EntityNotFoundException(ERROR_MESSAGE_BY_USER);
        }
    }
}