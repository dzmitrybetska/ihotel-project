package by.academy.project.hotel.dto.datauser;

import by.academy.project.hotel.entities.user.Passport;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataUserForManager {
    private final String id;
    private String name;
    private String surname;
    private Passport passport;
    private String email;
    private String phone;
}
