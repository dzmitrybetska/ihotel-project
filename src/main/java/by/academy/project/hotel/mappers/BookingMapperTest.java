//package by.academy.project.hotel.mappers;
//
//import by.academy.project.hotel.dto.BookingRequest;
//import by.academy.project.hotel.dto.BookingResponse;
//import by.academy.project.hotel.entities.booking.Booking;
//import org.mapstruct.InheritConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//@Mapper(uses = {UserMapperTest.class, RoomMapperTest.class}, componentModel = "spring")
//public interface BookingMapperTest {
//
//    @Mapping(source = "booking.user", target = "user")
//    BookingResponse toDto(Booking booking);
//
//    @InheritConfiguration
//    Booking toModel(BookingRequest bookingRequest);
//
//    @Mapping(source = "bookingRequest.user", target = "user")
//    @Mapping(source = "bookingRequest.rooms", target = "rooms")
//    void updateModel(BookingRequest bookingRequest, @MappingTarget Booking booking);
//}
