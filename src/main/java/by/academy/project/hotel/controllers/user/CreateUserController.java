package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.user.UserMapper;
import by.academy.project.hotel.mappers.user.UserMapperExt;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/create", loadOnStartup = 0)
public class CreateUserController extends HttpServlet {
    private UserService userService;
    private UserMapper mapper;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("user");
        if (userFromSession == null){
            User user = userService.createUser(createUserByGuest(req));
            req.getRequestDispatcher(SUCCESSFUL_USER_CREATION_GUEST_PAGE).forward(req, resp);
            session.setAttribute("user", user);
        } else if (userFromSession.getRole() == Role.MANAGER){
            userService.createUser(createUserByManager(req));
            req.getRequestDispatcher(SUCCESSFUL_USER_CREATION).forward(req, resp);
        } else if (userFromSession.getRole() == Role.ADMIN) {
            userService.createUser(createUserByAdmin(req));
            req.getRequestDispatcher(SUCCESSFUL_USER_CREATION).forward(req, resp);
        }else{
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
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
        userService = UserServiceImpl.getInstance();
        mapper = new UserMapperExt();
        config.getServletContext().setAttribute("userService", userService);
        config.getServletContext().setAttribute("mapper", mapper);
    }
}
