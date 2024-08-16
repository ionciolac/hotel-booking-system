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
public class UpdateFeedbackRequest {

    private UUID id;
    @JsonProperty("user_message")
    private String userMessage;
    @JsonProperty("user_mark")
    private FeedbackMark userMark;
}
