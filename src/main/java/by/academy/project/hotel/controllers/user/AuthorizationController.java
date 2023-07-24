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

@WebServlet(urlPatterns = "/user/authorization")
public class AuthorizationController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        HttpSession session = req.getSession();
        try {
            UserDto userDto = userService.getUserByLogin(login);
            session.setAttribute(USER, userDto);
        } catch (NotFoundUserException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher(ACCOUNT_CONTROLLER).forward(req, resp);
    }
}
