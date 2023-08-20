package by.academy.project.hotel.services.user;


import by.academy.project.hotel.dto.UserRequest;
import by.academy.project.hotel.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> read();

    UserResponse update(Long id, UserRequest userRequest);

    boolean delete(Long id);

    UserResponse findUserByID(Long id);

    UserResponse findUserByLogin(String login);

    List<UserResponse> findUsersByNameAndSurname(String name, String surname);
}
