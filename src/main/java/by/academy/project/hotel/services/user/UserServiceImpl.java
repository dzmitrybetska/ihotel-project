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

public final class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private final UserRepository userRepository;
    private final UserMapper mapper = UserMapper.getInstance();

    private UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public UserDto create(UserDto userDto) throws UserNotCreatedException {
        Optional<User> optional = userRepository.add(userDto);
        UserDto newUserDto;
        if (optional.isPresent()) {
            newUserDto = mapper.buildUserDto(optional.get());
        } else {
            throw new UserNotCreatedException(USER_CREATION_ERROR_MESSAGE);
        }
        return newUserDto;
    }

    @Override
    public List<UserDto> read() {
        List<User> users = userRepository.read();
        return mapper.buildUsersDto(users);
    }

    @Override
    public UserDto update(UserDto userDto) throws NotFoundUserException {
        Optional<User> optional = userRepository.update(userDto);
        if (optional.isPresent()) {
            return mapper.buildUserDto(optional.get());
        } else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> optional = userRepository.delete(id);
        return optional.isPresent();
    }

    @Override
    public UserDto getByID(Long id) throws NotFoundUserException {
        Optional<User> optional = userRepository.getByID(id);
        UserDto userDto;
        if (optional.isPresent()) {
            userDto = mapper.buildUserDto(optional.get());
        } else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
        return userDto;
    }

    @Override
    public UserDto getUserByLogin(String login) throws NotFoundUserException {
        Optional<User> optional = userRepository.getUserByLogin(login);
        UserDto userDto;
        if (optional.isPresent()) {
            userDto = mapper.buildUserDto(optional.get());
        } else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
        return userDto;
    }

    @Override
    public List<UserDto> findUser(String name, String surname) {
        List<User> users = userRepository.findUser(name, surname);
        return mapper.buildUsersDto(users);
    }

}