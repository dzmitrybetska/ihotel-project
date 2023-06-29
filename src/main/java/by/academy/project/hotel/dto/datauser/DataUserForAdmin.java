package by.academy.project.hotel.dto.datauser;

import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataUserForAdmin {
    private final String id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Passport passport;
    private String email;
    private String phone;
    private Role role;
}
