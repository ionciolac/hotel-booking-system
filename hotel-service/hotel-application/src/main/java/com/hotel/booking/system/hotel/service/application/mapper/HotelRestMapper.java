package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.hotel.service.application.data.req.hotel.CreateHotelRequest;
import com.hotel.booking.system.hotel.service.application.data.req.hotel.UpdateHotelRequest;
import com.hotel.booking.system.hotel.service.application.data.res.hotel.HotelResponse;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelRestMapper {

    @Mapping(target = "id", ignore = true)
    Hotel toHotel(CreateHotelRequest createHotelRequest);

    Hotel toHotel(UpdateHotelRequest updateHotelRequest);

    HotelResponse toHotelResponse(Hotel hotel);
}
