package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.userdto.UserMapperDto;
import by.academy.project.hotel.mappers.userdto.UserMapperDtoExt;
import by.academy.project.hotel.services.user.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.academy.project.hotel.util.configuration.Constants.*;

@WebServlet(urlPatterns = "/users/read")
public class ReadUsersController extends HttpServlet {
    private UserService userService;
    private final UserMapperDto mapperDto = new UserMapperDtoExt();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        List<User> users = userService.readUsers();
        if (userSession.getRole() == Role.MANAGER){
            req.setAttribute("users", mapperDto.readDataUsersForManager(users));
            req.getRequestDispatcher(USERS_PAGE_FOR_MANAGER).forward(req, resp);
        } else if (userSession.getRole() == Role.ADMIN) {
            req.setAttribute("users", mapperDto.readDataUsersForAdmin(users));
            req.getRequestDispatcher(USERS_PAGE_FOR_ADMIN).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
