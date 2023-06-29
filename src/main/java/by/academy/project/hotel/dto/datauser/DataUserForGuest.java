package by.academy.project.hotel.dto.datauser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataUserForGuest {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String phone;
}
