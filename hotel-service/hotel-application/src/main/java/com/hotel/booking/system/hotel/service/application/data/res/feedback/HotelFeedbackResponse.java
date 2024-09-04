package com.hotel.booking.system.hotel.service.application.data.res.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.application.data.res.feedback.FeedbackResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HotelFeedbackResponse extends FeedbackResponse {

    @JsonProperty("hotel_id")
    private UUID hotelId;
}
