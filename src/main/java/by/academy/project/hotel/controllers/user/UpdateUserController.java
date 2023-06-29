package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.user.UserMapper;
import by.academy.project.hotel.services.user.UserService;

import javax.servlet.ServletConfig;
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
    private UserMapper mapper;
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        User user = null;

        if (userSession.getRole() == Role.GUEST){
            user = userService.updateUser(userSession.getId(), createUserByGuest(req));
            session.setAttribute("user", user);
        } else if (userSession.getRole() == Role.MANAGER) {
            user = userService.updateUser(req.getParameter("id"), createUserByManager(req));
        }else if (userSession.getRole() == Role.ADMIN) {
            user = userService.updateUser(req.getParameter("id"),createUserByAdmin(req));
        }else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }

        if (user != null){
                req.getRequestDispatcher(SUCCESSFUL_UPDATE).forward(req, resp);
            } else {
                req.getRequestDispatcher(UNSUCCESSFUL_UPDATE).forward(req, resp);
            }
    }

    private User createUserByGuest(HttpServletRequest req){
        return mapper.buildUserByGuest(
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("email"),
                req.getParameter("phone")
        );
    }


    private User createUserByManager(HttpServletRequest req){
        return mapper.buildUserByManager(
                req.getParameter("name"),
                req.getParameter("surname"),
                new Passport(req.getParameter("passportSeries"),
                        req.getParameter("passportID"),
                        Country.valueOf(req.getParameter("country").toUpperCase())),
                req.getParameter("email"),
                req.getParameter("phone")
        );
    }
    private User createUserByAdmin(HttpServletRequest req){
        return mapper.buildUserByAdmin(
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("login"),
                req.getParameter("password"),
                new Passport(req.getParameter("passportSeries"),
                        req.getParameter("passportID"),
                        Country.valueOf(req.getParameter("country").toUpperCase())),
                req.getParameter("email"),
                req.getParameter("phone"),
                Role.valueOf(req.getParameter("userRole").toUpperCase())
        );
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        mapper = (UserMapper) config.getServletContext().getAttribute("mapper");
    }
}
