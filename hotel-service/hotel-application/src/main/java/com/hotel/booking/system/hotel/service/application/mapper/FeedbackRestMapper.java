package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.common.common.enums.FeedbackMark;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.FeedbackResponse;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.hotel.booking.system.common.common.enums.FeedbackMark.*;

@Mapper(componentModel = "spring")
public interface FeedbackRestMapper {

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    Feedback toFeedback(CreateFeedbackRequest createFeedbackRequest);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userFullName", ignore = true)
    @Mapping(target = "hotelId", ignore = true)
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    Feedback toFeedback(UpdateFeedbackRequest updateFeedbackRequest);

    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromEnumToValue")
    FeedbackResponse toFeedbackResponse(Feedback feedback);
}
