package by.academy.project.hotel.services.user;

import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.repositories.user.UserRepository;
import by.academy.project.hotel.repositories.user.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

public final class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private final UserRepository userRepository;

    private UserServiceImpl(){
        userRepository = new UserRepositoryImpl();
    }

    public static UserServiceImpl getInstance(){
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public List<User> readUsers() {
        return userRepository.readUsers();
    }

    @Override
    public User deleteUser(String id){
       return userRepository.deleteUser(id);
    }

    @Override
    public User updateUser(String id, User user){
        return userRepository.updateUser(id, user);
    }

    @Override
    public List<User> findUsers(String name, String surname){
      return userRepository.findUser(name, surname);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

}

