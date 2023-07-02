package by.academy.project.hotel.repositories.user;


import by.academy.project.hotel.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> readUsers() {
        return users;
    }
    @Override
    public Optional<User> updateUser(String id, User user) {
        Optional<User> optional = getUserByUserID(id);
        if (optional.isPresent()){
            User userFound = optional.get();
            userFound.setName(user.getName())
                    .setSurname(user.getSurname())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone())
                    .setRole(user.getRole());
            if(user.getPassword() != null){
                userFound.setPassword(user.getPassword());
            }
            if (user.getPassport() !=null){
                userFound.setPassport(user.getPassport());
            }
        }
        return optional;
    }

    @Override
    public Optional<User> deleteUser(String id) {
        Optional<User> optional = getUserByUserID(id);
        optional.ifPresent(users::remove);
        return optional;
    }

    private Optional<User> getUserByUserID(String id){
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findAny();
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }

    @Override
    public List<User> findUser(String name, String surname){
        return users.stream()
                .filter(user -> user.getName().equals(name) && user.getSurname().equals(surname))
                .collect(Collectors.toList());
    }
}
