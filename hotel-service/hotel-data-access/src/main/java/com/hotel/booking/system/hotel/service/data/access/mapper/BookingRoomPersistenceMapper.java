package com.hotel.booking.system.hotel.service.data.access.mapper;

import com.hotel.booking.system.hotel.service.data.access.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookingRoomPersistenceMapper {

    @Named("withoutRoomBookingsInRoom")
    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Room roomEntityToRoom(RoomEntity roomEntity);

    @Mapping(target = "room", qualifiedByName = "withoutRoomBookingsInRoom")
    RoomBooking toRoomBooking(RoomBookingEntity roomBookingEntity);

    RoomBookingEntity toRoomBookingEntity(RoomBooking roomBooking);
}
