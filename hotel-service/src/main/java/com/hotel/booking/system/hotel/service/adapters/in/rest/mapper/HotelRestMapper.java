package com.hotel.booking.system.hotel.service.adapters.in.rest.mapper;


import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel.CreateHotelRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.hotel.UpdateHotelRequest;
import com.hotel.booking.system.hotel.service.adapters.in.rest.data.res.hotel.HotelResponse;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HotelRestMapper {

    @Mapping(target = "id", ignore = true)
    Hotel toHotel(CreateHotelRequest createHotelRequest);

    Hotel toHotel(UpdateHotelRequest updateHotelRequest);

    HotelResponse toHotelResponse(Hotel hotel);
}
