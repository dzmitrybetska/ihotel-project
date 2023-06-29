package by.academy.project.hotel.mappers.userdto;


import by.academy.project.hotel.dto.datauser.DataUserForAdmin;
import by.academy.project.hotel.dto.datauser.DataUserForGuest;
import by.academy.project.hotel.dto.datauser.DataUserForManager;
import by.academy.project.hotel.entities.user.User;

import java.util.List;

public abstract class UserMapperDto {
    public abstract DataUserForGuest buildDataUserForGuest(User user);
    public abstract DataUserForManager buildDataUserForManager (User user);
    public abstract DataUserForAdmin buildDataUserForAdmin (User user);
    public abstract List<DataUserForManager> readDataUsersForManager(List<User> users);
    public abstract List<DataUserForAdmin> readDataUsersForAdmin(List<User> users);
}
