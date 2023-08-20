//package by.academy.project.hotel.mappers;
//
//import by.academy.project.hotel.dto.UserRequest;
//import by.academy.project.hotel.dto.UserResponse;
//import by.academy.project.hotel.entities.user.User;
//import org.mapstruct.InheritConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(uses = BookingMapperTest.class, componentModel = "spring")
//public interface UserMapperTest {
//
//    @Mapping(source = "user.bookings", target = "bookings")
//    UserResponse toDto(User user);
//
//    @InheritConfiguration
//    User toModel(UserRequest userRequest);
//}
