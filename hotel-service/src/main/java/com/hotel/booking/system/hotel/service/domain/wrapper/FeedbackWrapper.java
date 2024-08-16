package com.hotel.booking.system.hotel.service.domain.wrapper;

import com.hotel.booking.system.hotel.service.domain.model.Feedback;
import com.hotel.booking.system.hotel.service.domain.model.Hotel;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackWrapper {

    private Feedback feedback;
    private Hotel hotel;
}
