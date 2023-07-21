package by.academy.project.hotel.dto;

import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Passport passport;
    private String email;
    private String phone;
    private Role role;
}
