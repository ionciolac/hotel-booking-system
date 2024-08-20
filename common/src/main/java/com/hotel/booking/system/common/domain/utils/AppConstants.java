package com.hotel.booking.system.common.domain.utils;

import com.hotel.booking.system.common.common.FeedbackMark;

import java.util.List;

public class AppConstants {

    public static final List<Double> FEEDBACK_MARK_VALUES = List.of(FeedbackMark.ONE.mark, FeedbackMark.TWO.mark,
            FeedbackMark.THREE.mark, FeedbackMark.FOUR.mark, FeedbackMark.FIVE.mark);
    public static final int SYSTEM_CHECKIN_HOUR = 14;
    public static final int SYSTEM_CHECKOUT_HOUR = 10;
}
