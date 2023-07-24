package by.academy.project.hotel.repositories.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, UserDto, User> {
    Optional<User> getUserByLogin(String login);

    List<User> findUser(String name, String surname);
}
