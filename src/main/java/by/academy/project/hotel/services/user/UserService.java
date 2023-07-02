package by.academy.project.hotel.services.user;


import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> readUsers();
    User updateUser(String id, User user) throws NotFoundUserException;
    User deleteUser(String id) throws NotFoundUserException;
    User getUserByLogin(String login);
    List<User> findUsers(String name, String surname);
}
