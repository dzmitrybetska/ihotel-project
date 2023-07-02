package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.user.UserMapper;
import by.academy.project.hotel.mappers.user.UserMapperExt;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/create")
public class CreateUserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    private final UserMapper mapper = UserMapperExt.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute(USER);
        if (userFromSession == null){
            User user = userService.createUser(createUserByGuest(req));
            req.getRequestDispatcher(SUCCESSFUL_USER_CREATION_GUEST_PAGE).forward(req, resp);
            session.setAttribute(USER, user);
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
                req.getParameter(NAME),
                req.getParameter(SURNAME),
                req.getParameter(LOGIN),
                req.getParameter(PASSWORD),
                req.getParameter(EMAIL),
                req.getParameter(PHONE)
        );
    }
    private User createUserByManager(HttpServletRequest req){
        return mapper.buildUserByManager(
                req.getParameter(NAME),
                req.getParameter(SURNAME),
                new Passport(req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase())),
                req.getParameter(EMAIL),
                req.getParameter(PHONE)
        );
    }
    private User createUserByAdmin(HttpServletRequest req){
        return mapper.buildUserByAdmin(
                req.getParameter(NAME),
                req.getParameter(SURNAME),
                req.getParameter(LOGIN),
                req.getParameter(PASSWORD),
                new Passport(req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase())),
                req.getParameter(EMAIL),
                req.getParameter(PHONE),
                Role.valueOf(req.getParameter(ROLE).toUpperCase())
        );
    }
}