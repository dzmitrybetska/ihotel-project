package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.UserResponse;
import by.academy.project.hotel.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public List<UserResponse> read() {
        return userService.read();
    }

    @GetMapping(value = "/userById/{id}")
    public UserResponse findUserByID(@PathVariable Long id) {
        return userService.findUserByID(id);
    }

    @GetMapping(value = "/userByLogin/{login}")
    public UserResponse findUserByLogin(@PathVariable String login) {
        return userService.findUserByLogin(login);
    }

    @GetMapping(value = "/user/{name}, {surname}")
    public List<UserResponse> findUsersByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        return userService.findUsersByNameAndSurname(name, surname);
    }
}
