package by.academy.project.hotel.repositories.user;

import by.academy.project.hotel.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User createUser(User user);
    List<User> readUsers();
    User updateUser(String id, User user);
    User deleteUser(String id);
    Optional<User> getUserByLogin(String login);
    List<User> findUser(String name, String surname);
}
