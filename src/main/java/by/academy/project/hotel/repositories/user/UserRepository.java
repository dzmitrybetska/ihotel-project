package by.academy.project.hotel.repositories.user;

import by.academy.project.hotel.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    List<User> findUsersByNameAndSurname(String name, String surname);
}
