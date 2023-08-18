package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.dto.UserDto;
import by.academy.project.hotel.entities.user.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/rooms/read")
public class ReadRoomsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute(USER);
        List<RoomDto> roomDtos = userDto.getRole().readRoomsDto();
        req.setAttribute(ROOMS, roomDtos);
        redirectToPage(userDto.getRole(), req, resp);
    }

    private void redirectToPage(Role role, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (role == Role.GUEST) {
            req.getRequestDispatcher(ROOMS_PAGE_FOR_GUEST).forward(req, resp);
        } else if (role == Role.MANAGER) {
            req.getRequestDispatcher(ROOMS_PAGE_FOR_MANAGER).forward(req, resp);
        } else if (role == Role.ADMIN) {
            req.getRequestDispatcher(ROOMS_PAGE_FOR_ADMIN).forward(req, resp);
        } else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}