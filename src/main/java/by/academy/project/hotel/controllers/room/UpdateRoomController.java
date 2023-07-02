package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.mappers.room.RoomMapper;
import by.academy.project.hotel.mappers.room.RoomMapperExt;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.room.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/room/update")
public class UpdateRoomController extends HttpServlet {
    private final RoomService roomService = RoomServiceImpl.getInstance();
    private final RoomMapper mapper = RoomMapperExt.getInstance();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.getRole() == Role.ADMIN) {
            try{
                roomService.updateRoom(req.getParameter(ROOM_ID), createRoomForUpdateByAdmin(req));
                req.getRequestDispatcher(SUCCESSFUL_UPDATE_ROOM).forward(req, resp);
            }catch (NotFoundRoomException ex){
                req.setAttribute(ERROR, ex.getMessage());
                req.getRequestDispatcher(UNSUCCESSFUL_UPDATE_ROOM).forward(req, resp);
            }
        }else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }
    private Room createRoomForUpdateByAdmin(HttpServletRequest req){
        return mapper.buildRoom(req.getParameter(NUMBER),
                Double.parseDouble(req.getParameter(PRICE)),
                RoomCategory.valueOf(req.getParameter(ROOM_CATEGORY).toUpperCase()),
                RoomStatus.valueOf(req.getParameter(ROOM_STATUS).toUpperCase()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }
}
