package com.hotel.booking.system.hotel.service.application.data.res.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.enums.FeedbackMark;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private UUID id;
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
