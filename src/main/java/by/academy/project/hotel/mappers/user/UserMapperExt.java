package by.academy.project.hotel.mappers.user;


import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;

import java.util.UUID;

public class UserMapperExt extends UserMapper {
    @Override
    public User buildUserByGuest(String name, String surname, String login, String password, String email, String phone){
        return User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .phone(phone)
                .role(Role.GUEST)
                .build();
    }
    @Override
    public User buildUserByManager(String name, String surname, Passport passport, String email, String phone){
        return User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name(name)
                .surname(surname)
                .passport(passport)
                .email(email)
                .phone(phone)
                .role(Role.GUEST)
                .build();
    }
    @Override
    public User buildUserByAdmin(String name, String surname, String login, String password, Passport passport, String email, String phone, Role role){
        return User.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .passport(passport)
                .email(email)
                .phone(phone)
                .role(role)
                .build();
    }
}
