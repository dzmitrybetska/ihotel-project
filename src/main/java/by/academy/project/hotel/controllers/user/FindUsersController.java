package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.exceptions.NotFoundUserException;
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

@WebServlet(urlPatterns = "/user/find")
public class FindUsersController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final UserMapper mapper = UserMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute(USER);
        try {
            List<UserDto> foundUsers = userService.findUser(req.getParameter(NAME), req.getParameter(SURNAME));
            createUsersDto(userDto.getRole(), foundUsers, session, req, resp);
        } catch (NotFoundUserException e) {
            req.getRequestDispatcher(USER_NOT_FOUND).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void createUsersDto(Role role, List<UserDto> userDtos, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (role == Role.MANAGER) {
            session.setAttribute(FOUND_USERS, mapper.filterUsersDto(userDtos));
            req.getRequestDispatcher(FOUND_USERS_FOR_MANAGER).forward(req, resp);
        } else if (role == Role.ADMIN) {
            session.setAttribute(FOUND_USERS, userDtos);
            req.getRequestDispatcher(FOUND_USERS_FOR_ADMIN).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }
}