package by.academy.project.hotel.controllers.room;

import by.academy.project.hotel.dto.dataroom.DataRoomForAdmin;
import by.academy.project.hotel.dto.dataroom.DataRoomForGuest;
import by.academy.project.hotel.dto.dataroom.DataRoomForManager;
import by.academy.project.hotel.entities.room.Room;
import by.academy.project.hotel.entities.room.RoomStatus;
import by.academy.project.hotel.entities.user.Role;
import by.academy.project.hotel.entities.user.User;
import by.academy.project.hotel.mappers.roomdto.RoomMapperDto;
import by.academy.project.hotel.mappers.roomdto.RoomMapperDtoExt;
import by.academy.project.hotel.services.room.RoomService;
import by.academy.project.hotel.services.room.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static by.academy.project.hotel.util.configuration.Constants.*;


@WebServlet(urlPatterns = "/rooms/read")
public class ReadRoomsController extends HttpServlet {
    private final RoomService roomService = RoomServiceImpl.getInstance();
    private final RoomMapperDto mapperDto = RoomMapperDtoExt.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = roomService.readRooms();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);

        if (user == null || user.getRole() == Role.GUEST){
            List<DataRoomForGuest> roomsForGuest = rooms.stream()
                    .filter(room -> room.getRoomStatus() == RoomStatus.SERVICED)
                    .map(mapperDto::buildDataRoomForGuest)
                    .collect(Collectors.toList());
            req.setAttribute(ROOMS, roomsForGuest);
            req.getRequestDispatcher(ROOMS_PAGE_FOR_GUEST).forward(req, resp);
        } else if (user.getRole() == Role.MANAGER){
            List<DataRoomForManager> roomsForManager = rooms.stream()
                    .map(mapperDto::buildDataRoomForManager)
                    .collect(Collectors.toList());
            req.setAttribute(ROOMS, roomsForManager);
            req.getRequestDispatcher(ROOMS_PAGE_FOR_MANAGER).forward(req, resp);
        } else if (user.getRole() == Role.ADMIN) {
            List<DataRoomForAdmin> roomsForAdmin = rooms.stream()
                    .map(mapperDto::buildDataRoomForAdmin)
                    .collect(Collectors.toList());
            req.setAttribute(ROOMS, roomsForAdmin);
            req.getRequestDispatcher(ROOMS_PAGE_FOR_ADMIN).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}