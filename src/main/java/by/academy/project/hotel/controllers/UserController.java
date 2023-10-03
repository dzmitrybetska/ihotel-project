package by.academy.project.hotel.controllers;

import by.academy.project.hotel.aspects.SkipLogging;
import by.academy.project.hotel.dto.requests.UserRequest;
import by.academy.project.hotel.dto.responces.UserResponse;
import by.academy.project.hotel.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Operations on users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user")
    @Operation(description = "Add a new user")
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @SkipLogging
    @GetMapping(value = "/users")
    @Operation(description = "Get all users")
    public List<UserResponse> read() {
        return userService.read();
    }

    @PutMapping(value = "/user/{id}")
    @Operation(description = "Update user data by ID")
    public UserResponse update(@PathVariable @NotNull Long id, @Valid @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @DeleteMapping(value = "/user/{id}")
    @Operation(description = "Delete a user by ID")
    public void delete(@PathVariable @NotNull Long id) {
        userService.delete(id);
    }

    @GetMapping(value = "/user/{id}")
    @Operation(description = "Find a user by ID")
    public UserResponse findUserByID(@PathVariable @NotNull Long id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/userByLogin/{login}")
    @Operation(description = "Find a user by login")
    public UserResponse findUserByLogin(@PathVariable @NotBlank String login) {
        return userService.findUserByLogin(login);
    }

    @GetMapping(value = "/user/{name}/{surname}")
    @Operation(description = "Find a user by first and last name")
    public List<UserResponse> findUsersByNameAndSurname(@PathVariable @NotBlank String name, @PathVariable @NotBlank String surname) {
        return userService.findUsersByNameAndSurname(name, surname);
    }
}
