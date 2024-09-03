package com.hotel.booking.system.hotel.service.data.access.mapper;

import com.hotel.booking.system.hotel.service.data.access.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.HotelFeedbackEntity;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HotelFeedbackPersistenceMapper {

    @Named("withoutRoomHotelInEntityToHotel")
    @Mapping(target = "rooms", ignore = true)
    Hotel hotelEntityToHotel(HotelEntity hotelEntity);

    @Mapping(target = "hotel", qualifiedByName = "withoutRoomHotelInEntityToHotel")
    HotelFeedback toHotelFeedback(HotelFeedbackEntity hotelFeedbackEntity);

    HotelFeedbackEntity toFeedbackEntity(HotelFeedback hotelFeedback);
}
