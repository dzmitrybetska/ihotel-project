package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.exceptions.NotFoundUserException;
import by.academy.project.hotel.services.user.UserService;
import by.academy.project.hotel.services.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userService.deleteUser(req.getParameter(USER_ID));
            req.getRequestDispatcher(SUCCESSFUL_REMOVAL).forward(req, resp);
        }catch (NotFoundUserException ex){
            req.setAttribute(ERROR, ex.getMessage());
            req.getRequestDispatcher(UNSUCCESSFUL_REMOVAL).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }
}
