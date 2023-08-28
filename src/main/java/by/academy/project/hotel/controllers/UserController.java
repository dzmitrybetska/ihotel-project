package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.services.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user")
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @GetMapping(value = "/users")
    public List<UserResponse> read() {
        return userService.read();
    }

    @PutMapping(value = "/user/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @DeleteMapping(value = "/user/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/user/{id}")
    public UserResponse findUserByID(@PathVariable Long id) {
        return userService.findUserByID(id);
    }

    @GetMapping(value = "/userByLogin/{login}")
    public UserResponse findUserByLogin(@PathVariable @NotBlank String login) {
        return userService.findUserByLogin(login);
    }

    @GetMapping(value = "/user/{name}, {surname}")
    public List<UserResponse> findUsersByNameAndSurname(@PathVariable @NotBlank String name, @PathVariable @NotBlank String surname) {
        return userService.findUsersByNameAndSurname(name, surname);
    }
}
