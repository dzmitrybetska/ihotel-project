package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.exceptions.NotFoundUserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/read")
public class ReadUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userDtoFromSession = (UserDto) session.getAttribute(USER);
        try {
            UserDto userDto = userDtoFromSession.getRole().createUserDto(userDtoFromSession.getId());
            session.setAttribute(USER_DTO, userDto);
            redirectToPage(userDto.getRole(), req, resp);
        } catch (NotFoundUserException e) {
            req.getRequestDispatcher(USER_IS_NOT_FOUND).forward(req, resp);
        }
    }

    private void redirectToPage(Role role, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (role == Role.GUEST) {
            req.getRequestDispatcher(GUEST_DETAILS_PAGE).forward(req, resp);
        } else if (role == Role.MANAGER) {
            req.getRequestDispatcher(MANAGER_DETAILS_PAGE).forward(req, resp);
        } else if (role == Role.ADMIN) {
            req.getRequestDispatcher(ADMIN_DETAILS_PAGE).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }
}