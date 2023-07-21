package by.academy.project.hotel.filters;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;

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

@WebFilter(urlPatterns = "/user/update")
public class UpdateUserFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        HttpSession session = req.getSession();
        UserDto userFromSession = (UserDto) session.getAttribute(USER);
        if (userFromSession.getRole() == Role.MANAGER) {
            if (validEmail(email)) {
                chain.doFilter(req, res);
            } else {
                req.getRequestDispatcher(INVALID_EMAIL_ON_UPDATE).forward(req, res);
            }
        } else if (userFromSession.getRole() == Role.GUEST || userFromSession.getRole() == Role.ADMIN) {
            if (!validPassword(password)) {
                req.getRequestDispatcher(INVALID_PASSWORD_ON_UPDATE).forward(req, res);
            } else if (!validEmail(email)) {
                req.getRequestDispatcher(INVALID_EMAIL_ON_UPDATE).forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        }
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
}