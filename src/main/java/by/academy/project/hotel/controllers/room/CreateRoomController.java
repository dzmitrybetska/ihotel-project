package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategories;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.room.RoomMapper;
import by.academy.project.hotel.mappers.room.RoomMapperExt;
import by.academy.project.hotel.services.room.RoomService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.ACCESS_IS_DENIED;
import static by.academy.project.hotel.util.configuration.Constants.SUCCESSFUL_ROOM_CREATION;


@WebServlet(urlPatterns = "/room/create")
public class CreateRoomController extends HttpServlet {
    private RoomService roomService;
    private final RoomMapper roomMapper = new RoomMapperExt();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.ADMIN) {
            Room room = roomMapper
                    .buildRoom(
                            req.getParameter("number"),
                            Double.parseDouble(req.getParameter("price")),
                            RoomCategories.valueOf(req.getParameter("roomCategories").toUpperCase()),
                            RoomStatus.valueOf(req.getParameter("status").toUpperCase()));
            roomService.createRoom(room);
            req.getRequestDispatcher(SUCCESSFUL_ROOM_CREATION).forward(req, resp);
        }else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        roomService = (RoomService) config.getServletContext().getAttribute("roomService");
    }
}