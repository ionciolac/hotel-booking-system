package com.hotel.booking.system.hotel.service.adapters.in.rest.data.req.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.FeedbackMark;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedbackRequest {

    @JsonProperty("hotel_id")
    private UUID hotelId;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("user_full_name")
    private String userFullName;
    @JsonProperty("user_message")
    private String userMessage;
    @JsonProperty("user_mark")
    private FeedbackMark userMark;
}
