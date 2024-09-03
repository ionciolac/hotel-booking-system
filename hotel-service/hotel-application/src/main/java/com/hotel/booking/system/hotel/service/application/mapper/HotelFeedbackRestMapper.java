package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.common.common.enums.FeedbackMark;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateHotelFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.HotelFeedbackResponse;
import com.hotel.booking.system.hotel.service.domain.model.HotelFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.hotel.booking.system.common.common.enums.FeedbackMark.*;

@Mapper(componentModel = "spring")
public interface HotelFeedbackRestMapper {

    @Named("fromValueToEnum")
    default double fromValueToEnum(FeedbackMark feedbackMark) {
        if (feedbackMark == null)
            return 0;
        return switch (feedbackMark) {
            case ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
        };
    }

    @Named("fromEnumToValue")
    default FeedbackMark fromEnumToValue(double value) {
        return switch ((int) value) {
            case 1 -> ONE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            case 5 -> FIVE;
            default -> throw new IllegalStateException("Unexpected value: " + (int) value);
        };
    }

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
