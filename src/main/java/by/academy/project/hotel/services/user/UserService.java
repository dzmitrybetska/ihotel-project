package by.academy.project.hotel.services.user;


import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.exceptions.UserNotCreatedException;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto) throws UserNotCreatedException;

    List<UserDto> read();

    UserDto update(UserDto userDto) throws NotFoundUserException;

    boolean delete(Long id);

    UserDto getByID(Long id) throws NotFoundUserException;

    UserDto getUserByLogin(String login) throws NotFoundUserException;

    List<UserDto> findUser(String name, String surname) throws NotFoundUserException;
}
