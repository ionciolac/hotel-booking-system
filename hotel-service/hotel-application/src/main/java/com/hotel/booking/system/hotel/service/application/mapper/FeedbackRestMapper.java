package com.hotel.booking.system.hotel.service.application.mapper;

import com.hotel.booking.system.common.common.enums.FeedbackMark;
import org.mapstruct.Named;

import static com.hotel.booking.system.common.common.enums.FeedbackMark.*;

public interface FeedbackRestMapper {

    @Named("fromValueToEnum")
    default double fromValueToEnum(FeedbackMark feedbackMark) {
        if (feedbackMark == null)
            return 0;
        return switch (feedbackMark) {
            case ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
        };
    }

    @Named("fromEnumToValue")
    default FeedbackMark fromEnumToValue(double value) {
        return switch ((int) value) {
            case 1 -> ONE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            case 5 -> FIVE;
            default -> throw new IllegalStateException("Unexpected value: " + (int) value);
        };
    }
}
