package by.academy.project.hotel.services.user;

import by.academy.project.hotel.arguments.user.*;
import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing methods of the UserService")
public class UserServiceTest {

    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userMapper, userRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(UserCreateArguments.class)
    void createUserTest(UserRequest userRequest, User user, UserResponse userResponse) {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponse actualUserResponse = userService.create(userRequest);
        assertEquals(userResponse, actualUserResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(UserGetArguments.class)
    void readUsersTest(User user, UserResponse userResponse) {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<UserResponse> userResponseList = userService.read();
        assertEquals(Collections.singletonList(userResponse), userResponseList);
    }

    @ParameterizedTest
    @ArgumentsSource(UserUpdateArguments.class)
    void updateTest(UserRequest userRequest, User user, UserResponse userResponse) {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserResponse actualUserResponse = userService.update(user.getId(), userRequest);
        assertEquals(userResponse, actualUserResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(UserUpdateInvalidArguments.class)
    void updateExpectedException(User user, UserRequest userRequest) {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.update(user.getId(), userRequest));
    }

    @ParameterizedTest
    @ArgumentsSource(UserGetArguments.class)
    void findUserByIdTest(User user, UserResponse userResponse) {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        UserResponse actualUserResponse = userService.findUserByID(user.getId());
        assertEquals(userResponse, actualUserResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(UserInvalidArguments.class)
    void findUserByIdExpectedException(User user) {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByID(user.getId()));
    }

    @ParameterizedTest
    @ArgumentsSource(UserGetArguments.class)
    void findUserByLoginTest(User user, UserResponse userResponse) {
        when(userRepository.findUserByLogin(any(String.class))).thenReturn(Optional.of(user));
        UserResponse actualUserResponse = userService.findUserByLogin(user.getLogin());
        assertEquals(userResponse, actualUserResponse);
    }

    @ParameterizedTest
    @ArgumentsSource(UserInvalidArguments.class)
    void getUserByLoginExpectedException(User user) {
        when(userRepository.findUserByLogin(any(String.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByLogin(user.getLogin()));
    }

    @ParameterizedTest
    @ArgumentsSource(UserGetArguments.class)
    void findUsersByNameAndSurname(User user, UserResponse userResponse) {
        when(userRepository.findUsersByNameAndSurname(any(String.class), any(String.class))).thenReturn(List.of(user));
        List<UserResponse> actualUserResponse = userService.findUsersByNameAndSurname(user.getName(), user.getSurname());
        assertEquals(Collections.singletonList(userResponse), actualUserResponse);
    }
}

