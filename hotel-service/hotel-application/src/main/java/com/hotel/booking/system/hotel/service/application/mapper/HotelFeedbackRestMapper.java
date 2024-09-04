package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.HotelFeedbackResponse;
import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelFeedbackRestMapper extends FeedbackRestMapper {

    @Mapping(target = "hotel.id", source = "hotelId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    HotelFeedback toHotelFeedback(CreateHotelFeedbackRequest createHotelFeedbackRequest);

    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userFullName", ignore = true)
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    HotelFeedback toHotelFeedback(UpdateHotelFeedbackRequest updateHotelFeedbackRequest);

    @Mapping(target = "hotelId", source = "hotel.id")
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromEnumToValue")
    HotelFeedbackResponse toHotelFeedbackResponse(HotelFeedback hotelFeedback);
}
