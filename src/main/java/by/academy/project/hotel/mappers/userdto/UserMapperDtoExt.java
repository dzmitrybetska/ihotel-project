package by.academy.project.hotel.mappers.userdto;


import by.academy.project.hotel.dto.datauser.DataUserForAdmin;
import by.academy.project.hotel.dto.datauser.DataUserForGuest;
import by.academy.project.hotel.dto.datauser.DataUserForManager;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;

import java.util.List;
import java.util.stream.Collectors;

public final class UserMapperDtoExt extends UserMapperDto {
    private static UserMapperDtoExt instance;
    private UserMapperDtoExt(){}

    public static UserMapperDtoExt getInstance() {
        if (instance == null){
            instance = new UserMapperDtoExt();
        }
        return instance;
    }

    @Override
    public DataUserForGuest buildDataUserForGuest(User user){
        return DataUserForGuest.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public DataUserForManager buildDataUserForManager (User user){
        return DataUserForManager.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
    @Override
    public DataUserForAdmin buildDataUserForAdmin (User user){
        return DataUserForAdmin.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .passport(user.getPassport())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<DataUserForManager> readDataUsersForManager(List<User> users) {
        return users.stream()
                .filter(user -> user.getRole() == Role.GUEST)
                .map(this::buildDataUserForManager)
                .collect(Collectors.toList());
    }

    @Override
    public List<DataUserForAdmin> readDataUsersForAdmin(List<User> users) {
        return users.stream()
                .map(this::buildDataUserForAdmin)
                .collect(Collectors.toList());
    }
}
