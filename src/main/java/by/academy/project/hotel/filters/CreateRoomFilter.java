package by.academy.project.hotel.filters;


import by.academy.project.hotel.entities.room.RoomCategory;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.exceptions.NotFoundRoomException;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.room.RoomServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebFilter(urlPatterns = "/room/create")
public class CreateRoomFilter extends HttpFilter {
    private final RoomService roomService = new RoomServiceImpl();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (checkRoomByNumber(req.getParameter(NUMBER))) {
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_NUMBER).forward(req, res);
        } else if (!checkRoomCategories(req.getParameter(ROOM_CATEGORY))) {
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_ROOM_TYPE).forward(req, res);
        } else if (!checkRoomStatus(req.getParameter(ROOM_STATUS))) {
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_STATUS).forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }

    private boolean checkRoomByNumber(String number) {
        try {
            roomService.getRoomByNumber(number);
            return true;
        } catch (NotFoundRoomException ex) {
            return false;
        }
    }

    private boolean checkRoomCategories(String roomCategory) {
        try {
            RoomCategory.valueOf(roomCategory.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private boolean checkRoomStatus(String roomStatus) {
        try {
            RoomStatus.valueOf(roomStatus.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
