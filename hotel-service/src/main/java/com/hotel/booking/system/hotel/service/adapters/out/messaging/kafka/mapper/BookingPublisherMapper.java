package com.hotel.booking.system.hotel.service.adapters.out.messaging.kafka.mapper;


import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.kafka.model.CreateBookingMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingPublisherMapper {

    @Mapping(target = "hotelId", ignore = true)
    @Mapping(target = "roomBookingId", source = "id")
    @Mapping(target = "status", constant = "ROOM_BOOKED")
    @Mapping(target = "roomId", source = "room.id")
    CreateBookingMessage toCreateBookingMessageROOM_BOOKED(RoomBooking roomBooking);

    @Mapping(target = "hotelId", ignore = true)
    @Mapping(target = "roomBookingId", source = "id")
    @Mapping(target = "status", constant = "ROOM_IS_ALREADY_BOOKED")
    @Mapping(target = "roomId", source = "room.id")
    CreateBookingMessage toCreateBookingMessageROOM_IS_ALREADY_BOOKED(RoomBooking roomBooking);
}
