package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.exceptions.NotFoundUserException;
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

@WebServlet(urlPatterns = "/user/update")
public class UpdateUserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userSession = (UserDto) session.getAttribute(USER);
        try {
            UserDto userDto = userService.getByID(Long.parseLong(req.getParameter(USER_ID)));
            userService.update(userSession.getRole().updateUserDto(userDto, req));
            req.getRequestDispatcher(SUCCESSFUL_UPDATE).forward(req, resp);
        } catch (NotFoundUserException e) {
            req.setAttribute(ERROR, e.getMessage());
            req.getRequestDispatcher(UNSUCCESSFUL_UPDATE).forward(req, resp);
        }
    }
}