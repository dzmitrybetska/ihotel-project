package by.academy.project.hotel.controllers;

import by.academy.project.hotel.aspects.SkipLogging;
import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.services.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @SkipLogging
    @GetMapping(value = "/users")
    public List<UserResponse> read() {
        return userService.read();
    }

    @PutMapping(value = "/user/{id}")
    public UserResponse update(@PathVariable @NotNull Long id, @Valid @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        userService.delete(id);
    }

    @GetMapping(value = "/user/{id}")
    public UserResponse findUserByID(@PathVariable @NotNull Long id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/userByLogin/{login}")
    public UserResponse findUserByLogin(@PathVariable @NotBlank String login) {
        return userService.findUserByLogin(login);
    }

    @GetMapping(value = "/user/{name}/{surname}")
    public List<UserResponse> findUsersByNameAndSurname(@PathVariable @NotBlank String name, @PathVariable @NotBlank String surname) {
        return userService.findUsersByNameAndSurname(name, surname);
    }
}
