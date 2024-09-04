package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.hotel.service.application.data.req.feedback.CreateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.req.feedback.UpdateRoomFeedbackRequest;
import com.hotel.booking.system.hotel.service.application.data.res.feedback.RoomFeedbackResponse;
import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomFeedbackRestMapper extends FeedbackRestMapper {

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
