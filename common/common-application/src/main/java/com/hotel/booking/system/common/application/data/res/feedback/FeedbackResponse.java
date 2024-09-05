package com.hotel.booking.system.common.application.data.res.feedback;

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
public class FeedbackResponse {

    private UUID id;
    @JsonProperty("user_id")
    private UUID customerId;
    @JsonProperty("customer_full_name")
    private String customerFullName;
    @JsonProperty("customer_message")
    private String customerMessage;
    @JsonProperty("customer_mark")
    private FeedbackMark customerMark;
}
