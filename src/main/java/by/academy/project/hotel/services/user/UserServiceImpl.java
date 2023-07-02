package by.academy.project.hotel.services.user;

import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.mappers.user.UserMapper;
import by.academy.project.hotel.mappers.user.UserMapperExt;
import by.academy.project.hotel.repositories.user.UserRepository;
import by.academy.project.hotel.repositories.user.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ERROR_MESSAGE_BY_USER;

public final class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private final UserRepository userRepository;

    private UserServiceImpl(){
        userRepository = new UserRepositoryImpl();
        UserMapper userMapper = UserMapperExt.getInstance();
        createUser(userMapper.buildUserByAdmin("Dzmitry", "Betska", "baxset", "19668891",
                new Passport("MP", "1332348", Country.ALGERIA), "d.betska@gmail.com", "375291913373", Role.ADMIN));

        createUser(userMapper.buildUserByAdmin("Julia", "Wong", "baxset2", "wertyq123",
                new Passport("FQ", "4356217", Country.CHINA), "julia@gmail.com", "75347654367", Role.MANAGER));
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
    public User deleteUser(String id) throws NotFoundUserException {
        Optional<User> optional = userRepository.deleteUser(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
    }

    @Override
    public User updateUser(String id, User user) throws NotFoundUserException {
        Optional<User> optional = userRepository.updateUser(id, user);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new NotFoundUserException(ERROR_MESSAGE_BY_USER);
        }
    }

    @Override
    public List<User> findUsers(String name, String surname){
        return userRepository.findUser(name, surname);
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> optional = userRepository.getUserByLogin(login);
        return optional.orElse(null);
    }

}

