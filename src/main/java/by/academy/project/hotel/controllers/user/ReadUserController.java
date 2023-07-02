package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.userdto.UserMapperDto;
import by.academy.project.hotel.mappers.userdto.UserMapperDtoExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/read")
public class ReadUserController extends HttpServlet {
    private final UserMapperDto mapperDto = UserMapperDtoExt.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute(USER);
        if (userFromSession.getRole() == Role.GUEST){
            session.setAttribute(USER_DTO, mapperDto.buildDataUserForGuest(userFromSession));
            req.getRequestDispatcher(GUEST_DETAILS_PAGE).forward(req, resp);
        } else if (userFromSession.getRole() == Role.MANAGER) {
            session.setAttribute(USER_DTO, mapperDto.buildDataUserForManager(userFromSession));
            req.getRequestDispatcher(MANAGER_DETAILS_PAGE).forward(req, resp);
        } else if (userFromSession.getRole() == Role.ADMIN) {
            session.setAttribute(USER_DTO, mapperDto.buildDataUserForAdmin(userFromSession));
            req.getRequestDispatcher(ADMIN_DETAILS_PAGE).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }
}