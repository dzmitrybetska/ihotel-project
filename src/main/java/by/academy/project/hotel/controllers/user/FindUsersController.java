package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.userdto.UserMapperDto;
import by.academy.project.hotel.mappers.userdto.UserMapperDtoExt;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.academy.project.hotel.util.configuration.Constants.*;

@WebServlet(urlPatterns = "/user/find")
public class FindUsersController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapperDto mapperDto = UserMapperDtoExt.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute(USER);
        List<User> foundUsers = userService.findUsers(req.getParameter(NAME), req.getParameter(SURNAME));

        if (foundUsers.size() != 0) {
            if (userFromSession.getRole() == Role.MANAGER) {
                session.setAttribute(FOUND_USERS, mapperDto.readDataUsersForManager(foundUsers));
                req.getRequestDispatcher(FOUND_USERS_FOR_MANAGER).forward(req, resp);
            } else if (userFromSession.getRole() == Role.ADMIN) {
                session.setAttribute(FOUND_USERS, mapperDto.readDataUsersForAdmin(foundUsers));
                req.getRequestDispatcher(FOUND_USERS_FOR_ADMIN).forward(req, resp);
            } else {
                req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
            }
        } else {
            req.getRequestDispatcher(USER_NOT_FOUND).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}