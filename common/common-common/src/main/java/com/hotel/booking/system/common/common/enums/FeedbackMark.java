package com.hotel.booking.system.common.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackMark {
    ONE(1.0),
    TWO(2.0),
    THREE(3.0),
    FOUR(4.0),
    FIVE(5.0);

    public final double mark;
}
