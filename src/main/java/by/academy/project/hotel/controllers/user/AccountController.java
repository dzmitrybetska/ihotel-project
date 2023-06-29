package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/user/account")
public class AccountController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.MANAGER){
            req.getRequestDispatcher(MANAGER_ACCOUNT).forward(req, resp);
        } else if (user.getRole() == Role.ADMIN) {
            req.getRequestDispatcher(ADMIN_ACCOUNT).forward(req, resp);
        } else {
            req.getRequestDispatcher(GUEST_ACCOUNT).forward(req, resp);
        }
    }
}
