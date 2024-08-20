package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomWithHotelPersistenceMapper {


    @Named("ignoreRoomsFromMappingHotelEntityToHotel")
    @Mapping(target = "rooms", ignore = true)
    Hotel hotelEntityToHotel(HotelEntity hotelEntity);

    @Mapping(target = "hotel", qualifiedByName = "ignoreRoomsFromMappingHotelEntityToHotel")
    @Mapping(target = "roomBookings", ignore = true)
    Room toRoom(RoomEntity roomEntity);
}
