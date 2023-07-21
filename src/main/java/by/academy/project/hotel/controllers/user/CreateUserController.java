package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.exceptions.UserNotCreatedException;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/create")
public class CreateUserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userDtoFromSession = (UserDto) session.getAttribute(USER);
        UserDto userDto;
        if (userDtoFromSession == null) {
            userDto = Role.GUEST.createUserDto(req);
            try {
                userDtoFromSession = userService.create(userDto);
                session.setAttribute(USER, userDtoFromSession);
                req.getRequestDispatcher(SUCCESSFUL_USER_CREATION_GUEST_PAGE).forward(req, resp);
            } catch (UserNotCreatedException e) {
                req.getRequestDispatcher(UNSUCCESSFUL_USER_CREATION_GUEST_PAGE).forward(req, resp);
            }

        } else {
            userDto = userDtoFromSession.getRole().createUserDto(req);
            try {
                userService.create(userDto);
            } catch (UserNotCreatedException e) {
                req.getRequestDispatcher(UNSUCCESSFUL_USER_CREATION).forward(req, resp);
            }
            req.getRequestDispatcher(SUCCESSFUL_USER_CREATION).forward(req, resp);
        }
    }
}