package by.academy.project.hotel.dto.requests;

import by.academy.project.hotel.entities.user.Address;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.utils.annotations.PhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Size(min = 5, max = 8)
    private String login;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    @NotNull
    private Passport passport;
    @Size(max = 20)
    @Email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phone;
    @NotNull
    private Role role;
    @Size(max = 3)
    @NotEmpty
    private List<Address> addresses;
}
