package by.academy.project.hotel.services.user;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.academy.project.hotel.entities.user.Role.ADMIN;
import static by.academy.project.hotel.entities.user.Role.GUEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing methods of the UserService")
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private static User testUser;
    private static UserRequest testUserRequest;
    private static UserResponse testUserResponse;
    private static User userForUpdate;
    private static UserRequest userRequestForUpdate;
    private static UserResponse userResponseForUpdate;

    @BeforeAll
    static void beforeAll() {
        testUser = User.builder()
                .id(12L)
                .name("Dmitry")
                .surname("Betska")
                .login("baxset")
                .password("tyrew123werty")
                .role(Role.GUEST)
                .build();
        testUserRequest = UserRequest.builder()
                .name("Dmitry")
                .surname("Betska")
                .login("baxset")
                .password("tyrew123werty")
                .role(Role.GUEST)
                .build();
        testUserResponse = UserResponse.builder()
                .id(12L)
                .name("Dmitry")
                .surname("Betska")
                .login("baxset")
                .password("tyrew123werty")
                .role(Role.GUEST)
                .build();
        userForUpdate = User.builder()
                .id(2L)
                .name("Iryna")
                .surname("Betska")
                .login("ira88ira")
                .password("wer432234ret")
                .role(ADMIN)
                .build();
        userRequestForUpdate = UserRequest.builder()
                .name("Eva")
                .surname("Betska")
                .login("eVa2016")
                .password("435635183e")
                .role(GUEST)
                .build();
        userResponseForUpdate = UserResponse.builder()
                .id(2L)
                .name("Eva")
                .surname("Betska")
                .login("eVa2016")
                .password("435635183e")
                .role(GUEST)
                .build();
    }

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userMapper, userRepository);
    }

    @Test
    void createUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        UserResponse userResponse = userService.create(testUserRequest);
        assertEquals(testUserResponse, userResponse);
    }

    @Test
    void readUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));
        List<UserResponse> userResponseList = userService.read();
        assertEquals(Collections.singletonList(testUserResponse), userResponseList);
    }

    @Test
    void updateTest() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userForUpdate));
        when(userRepository.save(any(User.class))).thenReturn(userForUpdate);
        UserResponse userResponse = userService.update(2L, userRequestForUpdate);
        assertSame(userResponseForUpdate.getName(), userResponse.getName());
        assertEquals(userResponseForUpdate, userResponse);
    }

    @Test
    void updateExpectedException() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.update(testUser.getId(), testUserRequest));
    }

    @Test
    void findUserByIDTest() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(testUser));
        UserResponse userResponse = userService.findUserByID(testUser.getId());
        assertEquals(testUserResponse, userResponse);
    }

    @Test
    void findUserByIDExpectedException() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByID(testUser.getId()));
    }

    @Test
    void findUserByLoginTest() {
        when(userRepository.findUserByLogin(any(String.class))).thenReturn(Optional.of(testUser));
        UserResponse userResponse = userService.findUserByLogin(testUser.getLogin());
        assertEquals(testUserResponse, userResponse);
    }

    @Test
    void getUserByLoginExpectedException() {
        when(userRepository.findUserByLogin(any(String.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.findUserByLogin(testUser.getLogin()));
    }

    @Test
    void findUsersByNameAndSurname() {
        when(userRepository.findUsersByNameAndSurname(any(String.class), any(String.class))).thenReturn(List.of(testUser));
        List<UserResponse> userResponseList = userService.findUsersByNameAndSurname(testUser.getName(), testUser.getSurname());
        assertEquals(Collections.singletonList(testUserResponse), userResponseList);
    }
}
