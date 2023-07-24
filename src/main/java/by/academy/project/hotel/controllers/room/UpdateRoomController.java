package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.room.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static by.academy.project.hotel.util.configuration.Constants.*;

@WebServlet(urlPatterns = "/room/update")
public class UpdateRoomController extends HttpServlet {
    private final RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RoomDto roomDto = roomService.getByID(Long.parseLong(req.getParameter(ROOM_ID)));
            roomService.update(updateRoomDto(roomDto, req));
            req.getRequestDispatcher(SUCCESSFUL_UPDATE_ROOM).forward(req, resp);
        } catch (NotFoundRoomException ex) {
            req.setAttribute(ERROR, ex.getMessage());
            req.getRequestDispatcher(UNSUCCESSFUL_UPDATE_ROOM).forward(req, resp);
        }
    }

    private RoomDto updateRoomDto(RoomDto roomDto, HttpServletRequest req) {
        return roomDto.setNumber(req.getParameter(NUMBER))
                .setPrice(BigDecimal.valueOf(Double.parseDouble(req.getParameter(PRICE))))
                .setRoomCategory(RoomCategory.valueOf(req.getParameter(ROOM_CATEGORY).toUpperCase()))
                .setIsBooked(Boolean.valueOf(req.getParameter(IS_BOOKED)))
                .setRoomStatus(RoomStatus.valueOf(req.getParameter(ROOM_STATUS).toUpperCase()));
    }

}
