package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.dto.RoomDto;
import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.RoomNotAddedException;
import by.academy.project.hotel.mappers.RoomMapper;
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


@WebServlet(urlPatterns = "/room/create")
public class CreateRoomController extends HttpServlet {
    private final RoomService roomService = new RoomServiceImpl();
    private final RoomMapper roomMapper = RoomMapper.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomDto roomDto = roomMapper
                .buildRoomDto(
                        req.getParameter(NUMBER),
                        BigDecimal.valueOf(Double.parseDouble(req.getParameter(PRICE))),
                        RoomCategory.valueOf(req.getParameter(ROOM_CATEGORY).toUpperCase()),
                        Boolean.valueOf(req.getParameter(IS_BOOKED)),
                        RoomStatus.valueOf(req.getParameter(ROOM_STATUS).toUpperCase()));
        try {
            roomService.add(roomDto);
            req.getRequestDispatcher(SUCCESSFUL_ROOM_CREATION).forward(req, resp);
        } catch (RoomNotAddedException e) {
            req.getRequestDispatcher(UNSUCCESSFUL_ROOM_CREATION).forward(req, resp);
        }
    }
}