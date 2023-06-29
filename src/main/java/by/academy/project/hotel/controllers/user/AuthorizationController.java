package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.services.user.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.ACCOUNT_CONTROLLER;

@WebServlet(urlPatterns = "/user/authorization")
public class AuthorizationController extends HttpServlet {
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        Optional<User> userOptional = userService.getUserByLogin(login);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.getRequestDispatcher(ACCOUNT_CONTROLLER).forward(req, resp);
            }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
