package by.academy.project.hotel.mappers.user;


import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;

public abstract class UserMapper {
    public abstract User buildUserByGuest(String name, String surname, String login, String password, String email,
                                          String phone);
    public abstract User buildUserByManager(String name, String surname, Passport passport, String email, String phone);
    public abstract User buildUserByAdmin(String name, String surname, String login, String password, Passport passport,
                                          String email, String phone, Role role);
}
