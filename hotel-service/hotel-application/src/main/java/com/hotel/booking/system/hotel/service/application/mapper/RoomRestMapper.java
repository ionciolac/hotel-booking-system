package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.hotel.service.application.data.req.room.CreateRoomRequest;
import com.hotel.booking.system.hotel.service.application.data.req.room.UpdateRoomRequest;
import com.hotel.booking.system.hotel.service.application.data.res.room.RoomResponse;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomRestMapper {

    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "hotel.id", source = "hotelId")
    Room toRoom(CreateRoomRequest createRoomRequest);

    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "floor", ignore = true)
    @Mapping(target = "doorNumber", ignore = true)
    @Mapping(target = "roomType", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "currency", ignore = true)
    Room toRoom(UpdateRoomRequest updateRoomRequest);

    @Mapping(target = "hotelId", source = "hotel.id")
    RoomResponse toRoomResponse(Room room);
}
