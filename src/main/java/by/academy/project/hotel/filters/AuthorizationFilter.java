package by.academy.project.hotel.filters;


import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.academy.project.hotel.util.configuration.Constants.INVALID_LOGIN;
import static by.academy.project.hotel.util.configuration.Constants.INVALID_PASSWORD;


@WebFilter(urlPatterns = "/user/authorization")
public class AuthorizationFilter extends HttpFilter {
    private final UserService userService = UserServiceImpl.getInstance();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Optional<User> userOptional = userService.getUserByLogin(req.getParameter("login"));
        if (userOptional.isEmpty()) {
            req.getRequestDispatcher(INVALID_LOGIN).forward(req, res);
        } else {
            User user = userOptional.get();
            if (!user.getPassword().equals(req.getParameter("password"))) {
                req.getRequestDispatcher(INVALID_PASSWORD).forward(req, res);
            }
        }
        chain.doFilter(req, res);
    }

}