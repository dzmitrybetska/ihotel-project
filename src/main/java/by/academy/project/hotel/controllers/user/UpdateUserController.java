package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Country;
import by.academy.project.hotel.entities.user.Passport;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundUserException;
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

@WebServlet(urlPatterns = "/user/update")
public class UpdateUserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute(USER);

        try {
            if (userSession.getRole() == Role.GUEST) {
                userService.updateUser(userSession.getId(), createUserForUpdateByGuest(req));
            } else if (userSession.getRole() == Role.MANAGER) {
                userService.updateUser(req.getParameter(USER_ID), createUserForUpdateByManager(req));
            } else if (userSession.getRole() == Role.ADMIN) {
                userService.updateUser(req.getParameter(USER_ID), createUserForUpdateByAdmin(req));
            } else {
                req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
            }
            req.getRequestDispatcher(SUCCESSFUL_UPDATE).forward(req, resp);
        }catch (NotFoundUserException ex){
            req.setAttribute(ERROR, ex.getMessage());
            req.getRequestDispatcher(UNSUCCESSFUL_UPDATE).forward(req, resp);
        }
    }

    private User createUserForUpdateByGuest(HttpServletRequest req){
        return User.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .password(req.getParameter(PASSWORD))
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.GUEST)
                .build();
    }
    private User createUserForUpdateByManager(HttpServletRequest req){
        return User.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .passport(new Passport(req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase())))
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.GUEST)
                .build();
    }
    private User createUserForUpdateByAdmin(HttpServletRequest req){
        return User.builder()
                .name(req.getParameter(NAME))
                .surname(req.getParameter(SURNAME))
                .password(req.getParameter(PASSWORD))
                .passport(new Passport(req.getParameter(PASSPORT_SERIES),
                        req.getParameter(PASSPORT_ID),
                        Country.valueOf(req.getParameter(COUNTRY).toUpperCase())))
                .email(req.getParameter(EMAIL))
                .phone(req.getParameter(PHONE))
                .role(Role.valueOf(req.getParameter(ROLE).toUpperCase()))
                .build();
    }
}
