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

import static by.academy.project.hotel.util.configuration.Constants.*;

@WebFilter(urlPatterns = "/user/authorization")
public class AuthorizationFilter extends HttpFilter {
    private final UserService userService = UserServiceImpl.getInstance();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = userService.getUserByLogin(req.getParameter(LOGIN));
        if (user == null) {
            req.getRequestDispatcher(INVALID_LOGIN_ON_AUTHORIZATION).forward(req, res);
        } else if (!user.getPassword().equals(req.getParameter(PASSWORD))){
            req.getRequestDispatcher(INVALID_PASSWORD_ON_AUTHORIZATION).forward(req, res);
        }else {
            chain.doFilter(req, res);
        }
    }
}