package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomBookingEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomPersistenceMapper {

    @Named("ignoreRoomFromMappingRoomBookingEntityToRoomBooking")
    @Mapping(target = "room", ignore = true)
    RoomBooking roomBookingEntityToRoomBooking(RoomBookingEntity roomBookingEntity);

    @Named("ignoreRoomsFromMappingHotelEntityToHotel")
    @Mapping(target = "rooms", ignore = true)
    Hotel hotelEntityToHotel(HotelEntity hotelEntity);

    @Mapping(target = "roomBookings", qualifiedByName = "ignoreRoomFromMappingRoomBookingEntityToRoomBooking")
    @Mapping(target = "hotel", qualifiedByName = "ignoreRoomsFromMappingHotelEntityToHotel")
    Room toRoom(RoomEntity roomEntity);

    RoomEntity toRoomEntity(Room room);
}
