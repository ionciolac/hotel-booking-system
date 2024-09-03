package com.hotel.booking.system.hotel.service.data.access.mapper;

import com.hotel.booking.system.hotel.service.data.access.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.data.access.entity.RoomFeedbackEntity;
import com.hotel.booking.system.hotel.service.domain.model.Room;
import com.hotel.booking.system.hotel.service.domain.model.RoomFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomFeedbackPersistenceMapper {

    @Named("withoutHotelInRoomEntityToRoom")
    @Mapping(target = "hotel", ignore = true)
    @Mapping(target = "roomBookings", ignore = true)
    Room roomEntityToRoom(RoomEntity roomEntity);

    @Mapping(target = "room", qualifiedByName = "withoutHotelInRoomEntityToRoom")
    RoomFeedback toRoomFeedback(RoomFeedbackEntity roomFeedbackEntity);

    RoomFeedbackEntity toRoomFeedbackEntity(RoomFeedback roomFeedback);
}
