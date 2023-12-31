package by.academy.project.hotel.services.user;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.utils.Constants.USER_NOT_FOUND_BY_ID;
import static by.academy.project.hotel.utils.Constants.USER_NOT_FOUND_BY_LOGIN;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> read() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser
                .map(user -> userMapper.updateUser(user, userRequest))
                .map(userRepository::save)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_NOT_FOUND_BY_ID, id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public Optional<User> findUserByIdForBooking(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        return optionalUser
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(format(USER_NOT_FOUND_BY_LOGIN, login)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findUsersByNameAndSurname(String name, String surname) {
        List<User> users = userRepository.findUsersByNameAndSurname(name, surname);
        return users.stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }
}