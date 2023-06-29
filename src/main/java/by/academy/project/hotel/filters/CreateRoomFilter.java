package by.academy.project.hotel.filters;


import by.academy.project.hotel.entities.room.RoomCategories;
import by.academy.project.hotel.entities.room.RoomStatus;
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
    private final RoomService roomService = RoomServiceImpl.getInstance();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (checkRoomByNumber(req.getParameter("number"))){
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_NUMBER).forward(req, res);
        }
        roomTypeCheck(req.getParameter("roomCategories"), req, res);
        roomStatusCheck(req.getParameter("status"), req, res);
        chain.doFilter(req, res);
    }

    private boolean checkRoomByNumber(String number){
        return roomService.findRoomByNumber(number).isPresent();
    }

    private void roomTypeCheck(String roomType, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            RoomCategories.valueOf(roomType.toUpperCase());
        } catch (IllegalArgumentException ex){
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_ROOM_TYPE).forward(req, res);
        }
    }

    private void roomStatusCheck(String roomStatus, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            RoomStatus.valueOf(roomStatus.toUpperCase());
        } catch (IllegalArgumentException ex){
            req.getRequestDispatcher(FAILED_CREATION_DUE_TO_STATUS).forward(req, res);
        }
    }
}
