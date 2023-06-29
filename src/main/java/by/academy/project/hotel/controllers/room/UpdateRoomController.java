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

import static by.academy.project.hotel.util.configuration.Constants.SUCCESSFUL_UPDATE;
import static by.academy.project.hotel.util.configuration.Constants.UNSUCCESSFUL_UPDATE;


@WebServlet(urlPatterns = "/room/update")
public class UpdateRoomController extends HttpServlet {
    private RoomService roomService;
    private final RoomMapper mapper = new RoomMapperExt();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.ADMIN) {
            Room room = roomService.updateRoom(req.getParameter("id"), mapper.buildRoom(req.getParameter("number"),
                    Double.parseDouble(req.getParameter("price")),
                    RoomCategories.valueOf(req.getParameter("roomCategory").toUpperCase()),
                    RoomStatus.valueOf(req.getParameter("status").toUpperCase()))
            );

            if (room != null) {
                req.getRequestDispatcher(SUCCESSFUL_UPDATE).forward(req, resp);
            } else {
                req.getRequestDispatcher(UNSUCCESSFUL_UPDATE).forward(req, resp);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        roomService = (RoomService) config.getServletContext().getAttribute("roomService");
    }
}
