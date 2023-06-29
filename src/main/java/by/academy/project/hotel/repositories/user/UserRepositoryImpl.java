package by.academy.project.hotel.repositories.user;


import by.academy.project.hotel.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> USERS = new ArrayList<>();

    @Override
    public User createUser(User user) {
        USERS.add(user);
        return user;
    }

    @Override
    public List<User> readUsers() {
        return USERS;
    }
    @Override
    public User updateUser(String id, User user) {
        Optional<User> optional = getUserByUserID(id);
        if (optional.isPresent()){
            User userFound = optional.get();
            userFound.setName(user.getName())
                    .setSurname(user.getSurname())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone());
            if(user.getLogin() != null && user.getPassword() != null){
                userFound.setLogin(user.getLogin())
                        .setPassword(user.getPassword());
            }
            if (user.getPassport() !=null){
                userFound.setPassport(user.getPassport());
            }
            if (user.getRole() != null){
                userFound.setRole(user.getRole());
            }
            return userFound;
        }
        return null;
    }

    @Override
    public User deleteUser(String id) {
        Optional<User> foundUser = getUserByUserID(id);
        if (foundUser.isPresent()){
           int index = USERS.indexOf(foundUser.get());
           return USERS.remove(index);
        }
        return null;
    }

    private Optional<User> getUserByUserID(String id){
        return USERS.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findAny();
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return USERS.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }

    @Override
    public List<User> findUser(String name, String surname){
        return USERS.stream()
                .filter(user -> user.getName().equals(name) && user.getSurname().equals(surname))
                .collect(Collectors.toList());
    }
}
