package by.academy.project.hotel.dto.responces;

import by.academy.project.hotel.entities.user.Address;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Passport passport;
    private String email;
    private String phone;
    private Role role;
    private List<Address> addresses;
    private List<BookingResponse> bookings;
}
