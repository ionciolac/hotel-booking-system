package com.hotel.booking.system.common.common.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class DateTimeUtils {

    public static LocalDateTime addHourAndMinutesToYYYYmmDD(LocalDate yyyyMMdd, int hour, int minute) {
        return LocalDateTime.of(yyyyMMdd.getYear(), yyyyMMdd.getMonth(), yyyyMMdd.getDayOfMonth(), hour, minute);
    }

    public static LocalDateTime addHourAndMinutesToYYYYmmDD(LocalDateTime yyyyMMdd, int hour, int minute) {
        return LocalDateTime.of(yyyyMMdd.getYear(), yyyyMMdd.getMonth(), yyyyMMdd.getDayOfMonth(), hour, minute);
    }
}
