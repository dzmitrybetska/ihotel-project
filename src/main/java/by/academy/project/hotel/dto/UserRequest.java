package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.user.Address;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequest {

    private String name;
    private String surname;
    private String login;
    private String password;
    private Passport passport;
    private String email;
    private String phone;
    private Role role;
    private List<Address> addresses;
}
