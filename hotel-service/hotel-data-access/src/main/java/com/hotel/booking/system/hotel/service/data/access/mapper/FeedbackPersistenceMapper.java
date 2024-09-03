package com.hotel.booking.system.hotel.service.data.access.mapper;

import com.hotel.booking.system.hotel.service.data.access.entity.FeedbackEntity;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackPersistenceMapper {

    @Mapping(target = "hotelId", source = "hotel.id")
    Feedback toFeedback(FeedbackEntity feedbackEntity);

    @Mapping(target = "hotel", ignore = true)
    FeedbackEntity toFeedbackEntity(Feedback feedback);
}
