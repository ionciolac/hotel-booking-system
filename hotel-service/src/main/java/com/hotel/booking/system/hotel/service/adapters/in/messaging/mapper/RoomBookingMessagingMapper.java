package com.hotel.booking.system.hotel.service.adapters.in.messaging.mapper;

import com.hotel.booking.system.common.messaging.RoomBookingRequest;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomBookingMessagingMapper {

    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "pricePerNight", ignore = true)
    @Mapping(target = "nightsNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "room.id", source = "roomId")
    RoomBooking toRoomBooking(RoomBookingRequest roomBookingRequest);
}
