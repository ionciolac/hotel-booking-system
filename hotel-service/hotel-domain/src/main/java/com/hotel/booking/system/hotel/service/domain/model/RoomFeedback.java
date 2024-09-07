package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.domain.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.hotel.booking.system.common.common.utils.AppConstants.FEEDBACK_MARK_VALUES;
import static org.springframework.util.StringUtils.hasText;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomFeedback extends Feedback {

    private Room room;

    public void patch(RoomFeedback target, RoomFeedback source) {
        if (hasText(source.getCustomerMessage())) {
            target.setCustomerMessage(source.getCustomerMessage());
        }
        if (FEEDBACK_MARK_VALUES.contains(source.getCustomerMark())) {
            target.setCustomerMark(source.getCustomerMark());
        }
    }
}
