package by.academy.project.hotel.services.user;


import by.academy.project.hotel.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> readUsers();
    User updateUser(String id, User user);
    User deleteUser(String id);
    Optional<User> getUserByLogin(String login);
    List<User> findUsers(String name, String surname);
}
