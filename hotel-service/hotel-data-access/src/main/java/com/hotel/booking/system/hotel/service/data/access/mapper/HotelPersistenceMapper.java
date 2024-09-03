package com.hotel.booking.system.hotel.service.data.access.mapper;

import com.hotel.booking.system.hotel.service.data.access.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HotelPersistenceMapper {

    @Named("ignoreRoomFromMappingRoomBookingEntityToRoomBooking")
    @Mapping(target = "room", ignore = true)
    RoomBooking roomBookingEntityToRoomBooking(RoomBookingEntity roomBookingEntity);

    @Named("ignoreHotelFromMappingRoomEntityToRoom")
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "roomBookings", qualifiedByName = "ignoreRoomFromMappingRoomBookingEntityToRoomBooking")
    Room roomEntityToRoom(RoomEntity roomEntity);

    @Mapping(target = "rooms", qualifiedByName = "ignoreHotelFromMappingRoomEntityToRoom")
    Hotel toHotel(HotelEntity hotelEntity);

    HotelEntity toHotelEntity(Hotel hotel);
}
