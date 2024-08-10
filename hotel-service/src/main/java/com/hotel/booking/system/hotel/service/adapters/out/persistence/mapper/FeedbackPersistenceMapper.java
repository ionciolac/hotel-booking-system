package com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.entity.FeedbackEntity;
import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface FeedbackPersistenceMapper {

    @Mapping(target = "hotelId", source = "hotel.id")
    Feedback toFeedback(FeedbackEntity feedbackEntity);

    @Mapping(target = "hotel", ignore = true)
    FeedbackEntity toFeedbackEntity(Feedback feedback);
}
