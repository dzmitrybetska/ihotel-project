package by.academy.project.hotel.filters;


import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebFilter(urlPatterns = "/user/create")
public class CreateUserFilter extends HttpFilter {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        HttpSession session = req.getSession();
        UserDto userFromSession = (UserDto) session.getAttribute(USER);

        if (userFromSession == null || userFromSession.getRole() == Role.ADMIN) {
            if (!validLogin(login)) {
                req.getRequestDispatcher(INVALID_LOGIN_ON_CREATE).forward(req, res);
            } else if (!validPassword(password)) {
                req.getRequestDispatcher(INVALID_PASSWORD_ON_CREATE).forward(req, res);
            } else if (checkLogin(login)) {
                req.getRequestDispatcher(LOGIN_EXISTS).forward(req, res);
            } else if (!validEmail(email)) {
                req.getRequestDispatcher(INVALID_EMAIL).forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } else if (userFromSession.getRole() == Role.MANAGER) {
            if (validEmail(email)) {
                chain.doFilter(req, res);
            } else {
                req.getRequestDispatcher(INVALID_EMAIL).forward(req, res);
            }
        }
    }

    private boolean validLogin(String login) {
        Pattern pattern = Pattern.compile(REGEX_LOGIN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private boolean validPassword(String password) {
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean validEmail(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkLogin(String login) {
        try {
            userService.getUserByLogin(login);
            return true;
        } catch (NotFoundUserException ex) {
            return false;
        }
    }
}
