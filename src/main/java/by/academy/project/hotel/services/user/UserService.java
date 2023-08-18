package by.academy.project.hotel.services.user;


import by.academy.project.hotel.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    List<UserDto> read();

    UserDto update(UserDto userDto);

    boolean delete(Long id);

    UserDto getByID(Long id);

    UserDto getUserByLogin(String login);

    List<UserDto> findUser(String name, String surname);
}
