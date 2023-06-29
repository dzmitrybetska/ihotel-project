package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
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


@WebServlet(urlPatterns = "/user/delete")
public class DeleteUserController extends HttpServlet {
    private UserService userService;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("user");
        if (userFromSession.getRole() == Role.ADMIN){
            if (userService.deleteUser(req.getParameter("id")) != null){
                req.getRequestDispatcher(SUCCESSFUL_REMOVAL).forward(req, resp);
            }
            req.getRequestDispatcher(UNSUCCESSFUL_REMOVAL).forward(req, resp);
        }else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
