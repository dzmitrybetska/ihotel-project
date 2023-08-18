package by.academy.project.hotel.controllers.user;

import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;

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
        UserDto userDto = (UserDto) session.getAttribute(USER);
        if (userDto.getRole() == Role.MANAGER) {
            req.getRequestDispatcher(MANAGER_ACCOUNT).forward(req, resp);
        } else if (userDto.getRole() == Role.ADMIN) {
            req.getRequestDispatcher(ADMIN_ACCOUNT).forward(req, resp);
        } else {
            req.getRequestDispatcher(GUEST_ACCOUNT).forward(req, resp);
        }
    }
}
