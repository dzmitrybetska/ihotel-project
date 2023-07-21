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

import static by.academy.project.hotel.util.configuration.Constants.ACCESS_IS_DENIED;
import static by.academy.project.hotel.util.configuration.Constants.USER;

@WebFilter(urlPatterns = {"/room/update", "/room/delete", "/room/create"})
public class AdminOperationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        UserDto userFromSession = (UserDto) session.getAttribute(USER);
        if (userFromSession.getRole() == Role.ADMIN) {
            chain.doFilter(req, res);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, res);
        }
    }
}
