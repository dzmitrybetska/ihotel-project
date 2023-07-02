package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
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


@WebServlet(urlPatterns = "/room/delete")
public class DeleteRoomController extends HttpServlet {
    private final RoomService roomService = RoomServiceImpl.getInstance();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user.getRole() == Role.ADMIN){
            try{
                roomService.deleteRoom(req.getParameter(ROOM_ID));
                req.getRequestDispatcher(SUCCESSFUL_REMOVAL_ROOM).forward(req, resp);
            } catch (NotFoundRoomException ex) {
                req.setAttribute(ERROR, ex.getMessage());
                req.getRequestDispatcher(UNSUCCESSFUL_REMOVAL_ROOM).forward(req, resp);
            }
        }else {
            req.getRequestDispatcher(ACCESS_IS_DENIED).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }
}