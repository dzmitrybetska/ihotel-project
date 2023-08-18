package by.academy.project.hotel.services.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.exceptions.UserNotCreatedException;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import by.academy.project.hotel.repositories.user.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_BY_USER;
import static by.academy.project.hotel.util.configuration.Constants.USER_CREATION_ERROR_MESSAGE;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.getInstance();

    public UserServiceImpl() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        Optional<User> optionalUser = userRepository.add(userDto);
        return optionalUser.map(userMapper::buildUserDto).orElseThrow(() -> new UserNotCreatedException(USER_CREATION_ERROR_MESSAGE));
    }

    @Override
    public List<UserDto> read() {
        List<User> users = userRepository.read();
        return userMapper.buildUsersDto(users);
    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<User> optionalUser = userRepository.update(userDto);
        return optionalUser.map(userMapper::buildUserDto).orElseThrow(() -> new NotFoundUserException(ERROR_MESSAGE_BY_USER));
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> optionalUser = userRepository.delete(id);
        return optionalUser.isPresent();
    }

    @Override
    public UserDto getByID(Long id) {
        Optional<User> optionalUser = userRepository.getByID(id);
        return optionalUser.map(userMapper::buildUserDto).orElseThrow(() -> new NotFoundUserException(ERROR_MESSAGE_BY_USER));
    }

    @Override
    public UserDto getUserByLogin(String login) {
        Optional<User> optionalUser = userRepository.getUserByLogin(login);
        return optionalUser.map(userMapper::buildUserDto).orElseThrow(() -> new NotFoundUserException(ERROR_MESSAGE_BY_USER));
    }

    @Override
    public List<UserDto> findUser(String name, String surname) {
        List<User> users = userRepository.findUser(name, surname);
        if (users.size() != 0) {
            return userMapper.buildUsersDto(users);
        } else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
    }
}