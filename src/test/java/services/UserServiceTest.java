package services;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.exceptions.UserNotCreatedException;
import by.academy.project.hotel.mappers.UserMapper;
import by.academy.project.hotel.repositories.user.UserRepository;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private static final UserMapper mapper = UserMapper.getInstance();
    private static User testUser;
    private static UserDto testUserDto;

    @BeforeClass
    public static void prepareTestEntity() {
        testUser = User.builder()
                .id(12L)
                .name("Dmitry")
                .surname("Betska")
                .login("baxset")
                .password("tyrew123werty")
                .role(Role.GUEST)
                .build();
        testUserDto = mapper.buildUserDto(testUser);
    }

    @Before
    public void init() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void createTest() throws UserNotCreatedException {
        when(userRepository.add(any(UserDto.class))).thenReturn(Optional.of(testUser));
        UserDto result = userService.create(testUserDto);
        assertEquals(result, testUserDto);
    }

    @Test(expected = UserNotCreatedException.class)
    public void createExpectedException() throws UserNotCreatedException {
        when(userRepository.add(any(UserDto.class))).thenReturn(Optional.empty());
        userService.create(testUserDto);
    }

    @Test
    public void readTest() {
        when(userRepository.read()).thenReturn(List.of(testUser));
        List<UserDto> result = userService.read();
        assertEquals(result.get(0), testUserDto);
    }

    @Test
    public void updateTest() throws NotFoundUserException {
        User userForUpdate = mapper.buildUser(testUserDto);
        userForUpdate.setName("Iryna")
                .setRole(Role.MANAGER)
                .setPassport(new Passport().setPassportID("2341256")
                        .setPassportSeries("MD"));
        UserDto userDtoForUpdate = mapper.buildUserDto(userForUpdate);
        when(userRepository.update(any(UserDto.class))).thenReturn(Optional.of(userForUpdate));
        UserDto result = userService.update(userDtoForUpdate);
        assertSame(result.getName(), userDtoForUpdate.getName());
        assertEquals(result, userDtoForUpdate);
    }

    @Test(expected = NotFoundUserException.class)
    public void updateExpectedException() throws NotFoundUserException {
        when(userRepository.update(any(UserDto.class))).thenReturn(Optional.empty());
        userService.update(testUserDto);
    }

    @Test
    public void deleteTest() {
        when(userRepository.delete(testUser.getId())).thenReturn(Optional.of(testUser));
        boolean result = userService.delete(testUser.getId());
        assertTrue(result);
    }

    @Test
    public void getByIdTest() throws NotFoundUserException {
        when(userRepository.getByID(any(Long.class))).thenReturn(Optional.of(testUser));
        UserDto result = userService.getByID(testUser.getId());
        assertEquals(result, testUserDto);
    }

    @Test(expected = NotFoundUserException.class)
    public void getByIdExpectedException() throws NotFoundUserException {
        when(userRepository.getByID(any(Long.class))).thenReturn(Optional.empty());
        userService.getByID(testUserDto.getId());
    }

    @Test
    public void getUserByLoginTest() throws NotFoundUserException {
        when(userRepository.getUserByLogin(any(String.class))).thenReturn(Optional.of(testUser));
        UserDto result = userService.getUserByLogin(testUserDto.getLogin());
        assertEquals(result, testUserDto);
    }

    @Test(expected = NotFoundUserException.class)
    public void getUserByLoginExpectedException() throws NotFoundUserException {
        when(userRepository.getUserByLogin(any(String.class))).thenReturn(Optional.empty());
        userService.getUserByLogin(testUserDto.getLogin());
    }

    @Test
    public void findUserTest() throws NotFoundUserException {
        when(userRepository.findUser(any(String.class), any(String.class))).thenReturn(List.of(testUser));
        List<UserDto> result = userService.findUser(testUserDto.getName(), testUserDto.getSurname());
        assertEquals(result.get(0), testUserDto);
    }

    @Test(expected = NotFoundUserException.class)
    public void findUserExpectedException() throws NotFoundUserException {
        when(userRepository.findUser(any(String.class), any(String.class))).thenReturn(List.of());
        userService.findUser(testUserDto.getName(), testUserDto.getSurname());
    }
}
