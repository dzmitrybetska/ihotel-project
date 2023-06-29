package by.academy.project.hotel.filters;


import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getRole() == Role.ADMIN) {
            if (!validLogin(login)) {
                req.getRequestDispatcher(INVALID_LOGIN).forward(req, res);
            } else if (!validPassword(password)) {
                req.getRequestDispatcher(INVALID_PASSWORD).forward(req, res);
            } else if (userService.getUserByLogin(login).isPresent()) {
                req.getRequestDispatcher(LOGIN_EXISTS).forward(req, res);
            } else if (!validEmail(email)) {
                req.getRequestDispatcher(INVALID_EMAIL).forward(req, res);
            }else {
                chain.doFilter(req, res);
            }
        }else if (user.getRole() == Role.MANAGER) {
            if (!validEmail(email)){
                req.getRequestDispatcher(INVALID_EMAIL).forward(req, res);
            }else {
                chain.doFilter(req, res);
            }
        }
    }

    private boolean validLogin (String login){
        Pattern pattern = Pattern.compile(REGEX_LOGIN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private boolean validPassword(String password) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean validEmail(String email){
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
