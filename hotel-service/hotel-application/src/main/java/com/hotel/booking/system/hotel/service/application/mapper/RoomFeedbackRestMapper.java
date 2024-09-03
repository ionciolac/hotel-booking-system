package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.common.common.enums.FeedbackMark;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.RoomFeedbackResponse;
import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static com.hotel.booking.system.common.common.enums.FeedbackMark.*;

@Mapper(componentModel = "spring")
public interface RoomFeedbackRestMapper {

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

    @Mapping(target = "room.id", source = "roomId")
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    RoomFeedback toRoomFeedback(CreateRoomFeedbackRequest createRoomFeedbackRequest);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromValueToEnum")
    RoomFeedback toRoomFeedback(UpdateRoomFeedbackRequest updateRoomFeedbackRequest);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "userMark", source = "userMark", qualifiedByName = "fromEnumToValue")
    RoomFeedbackResponse toRoomFeedbackResponse(RoomFeedback roomFeedback);
}
