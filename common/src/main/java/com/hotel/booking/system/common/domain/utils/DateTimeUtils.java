package com.hotel.booking.system.common.domain.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class DateTimeUtils {

    public static LocalDateTime addHourAndMinutesToYYYYmmDD(LocalDate yyyyMMMdd, int hour, int minute) {
        return LocalDateTime.of(yyyyMMMdd.getYear(), yyyyMMMdd.getMonth(), yyyyMMMdd.getDayOfMonth(), hour, minute);
    }

    public static LocalDateTime addHourAndMinutesToYYYYmmDD(LocalDateTime yyyyMMMdd, int hour, int minute) {
        return LocalDateTime.of(yyyyMMMdd.getYear(), yyyyMMMdd.getMonth(), yyyyMMMdd.getDayOfMonth(), hour, minute);
    }
}
