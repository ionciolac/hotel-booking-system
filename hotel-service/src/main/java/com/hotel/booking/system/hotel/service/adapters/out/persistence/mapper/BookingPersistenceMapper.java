package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookingPersistenceMapper {

    @Named("withoutRoomBookingsInRoom")
    @Mapping(target = "roomBookings", ignore = true)
    Room roomEntityToRoom(RoomEntity roomEntity);

    @Mapping(target = "room", qualifiedByName = "withoutRoomBookingsInRoom")
    RoomBooking toRoomBooking(RoomBookingEntity roomBookingEntity);

    RoomBookingEntity toRoomBookingEntity(RoomBooking roomBooking);
}
