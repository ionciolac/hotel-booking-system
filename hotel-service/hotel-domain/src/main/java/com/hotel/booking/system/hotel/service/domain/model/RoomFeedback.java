package com.hotel.booking.system.hotel.service.domain.model;

import com.hotel.booking.system.common.domain.Feedback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomFeedback extends Feedback {

    private Room room;
}
