//package by.academy.project.hotel.mappers;
//
//import by.academy.project.hotel.dto.RoomRequest;
//import by.academy.project.hotel.dto.RoomResponse;
//import by.academy.project.hotel.entities.room.Room;
//import org.mapstruct.InheritConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(uses = BookingMapperTest.class, componentModel = "spring")
//public interface RoomMapperTest {
//
//    @Mapping(source = "room.bookings", target = "bookings")
//    RoomResponse toDto(Room room);
//
//    @InheritConfiguration
//    Room toModel(RoomRequest roomRequest);
//}
