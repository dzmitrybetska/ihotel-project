package by.academy.project.hotel.entities.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private final String id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Passport passport;
    private String email;
    private String phone;
    private Role role;


    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setPassport(Passport passport) {
        this.passport = passport;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

}
