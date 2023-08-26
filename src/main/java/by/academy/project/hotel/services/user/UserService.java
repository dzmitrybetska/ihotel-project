package by.academy.project.hotel.services.user;


import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> read();

    UserResponse update(Long id, UserRequest userRequest);

    boolean delete(Long id);

    UserResponse findUserByID(Long id);

    Optional<User> findUserByIDForBooking(Long id);

    UserResponse findUserByLogin(String login);

    List<UserResponse> findUsersByNameAndSurname(String name, String surname);
}
