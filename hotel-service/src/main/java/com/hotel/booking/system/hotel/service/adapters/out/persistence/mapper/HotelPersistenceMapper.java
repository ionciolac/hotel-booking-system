package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelPersistenceMapper {

    Hotel toHotel(HotelEntity hotelEntity);

    HotelEntity toHotelEntity(Hotel hotel);
}
