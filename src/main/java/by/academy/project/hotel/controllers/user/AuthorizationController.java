package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;

@WebServlet(urlPatterns = "/user/authorization")
public class AuthorizationController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        User user = userService.getUserByLogin(login);
        HttpSession session = req.getSession();
        session.setAttribute(USER, user);
        req.getRequestDispatcher(ACCOUNT_CONTROLLER).forward(req, resp);
    }
}
