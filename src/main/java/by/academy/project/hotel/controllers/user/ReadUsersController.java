package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.mappers.UserMapper;
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

@WebServlet(urlPatterns = "/users/read")
public class ReadUsersController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final UserMapper mapper = UserMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userSession = (UserDto) session.getAttribute(USER);
        List<UserDto> userDtos = userService.read();
        createUsersDto(userSession.getRole(), userDtos, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void createUsersDto(Role role, List<UserDto> userDtos, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (role == Role.MANAGER) {
            req.setAttribute(USERS, mapper.filterUsersDto(userDtos));
            req.getRequestDispatcher(USERS_PAGE_FOR_MANAGER).forward(req, resp);
        } else if (role == Role.ADMIN) {
            req.setAttribute(USERS, userDtos);
            req.getRequestDispatcher(USERS_PAGE_FOR_ADMIN).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }
}

