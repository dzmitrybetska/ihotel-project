package by.academy.project.hotel.mappers.roomdto;


import by.academy.project.hotel.dto.dataroom.DataRoomForAdmin;
import by.academy.project.hotel.dto.dataroom.DataRoomForGuest;
import by.academy.project.hotel.dto.dataroom.DataRoomForManager;
import by.academy.project.hotel.entities.room.Room;

public final class RoomMapperDtoExt extends RoomMapperDto {
    private static RoomMapperDtoExt instance;
    private RoomMapperDtoExt(){}

    public static RoomMapperDtoExt getInstance() {
        if (instance == null){
            instance = new RoomMapperDtoExt();
        }
        return instance;
    }
    @Override
    public DataRoomForGuest buildDataRoomForGuest(Room room){
        return DataRoomForGuest.builder()
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .build();
    }
    @Override
    public DataRoomForManager buildDataRoomForManager(Room room) {
        return DataRoomForManager.builder()
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .build();
    }
    @Override
    public DataRoomForAdmin buildDataRoomForAdmin(Room room) {
        return DataRoomForAdmin.builder()
                .id(room.getId())
                .number(room.getNumber())
                .price(room.getPrice())
                .roomCategory(room.getRoomCategory())
                .isBooked(room.getIsBooked())
                .roomStatus(room.getRoomStatus())
                .build();
    }
}
