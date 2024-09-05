package com.hotel.booking.system.common.application.data.req.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.booking.system.common.common.enums.FeedbackMark;
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
public class UpdateFeedbackRequest {

    private UUID id;
    @JsonProperty("customer_message")
    private String customerMessage;
    @JsonProperty("customer_mark")
    private FeedbackMark customerMark;
}
