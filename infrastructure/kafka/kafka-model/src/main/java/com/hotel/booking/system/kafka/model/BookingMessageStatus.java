package com.hotel.booking.system.kafka.model;

public enum BookingMessageStatus {
    // create statuses
    CREATE_BOOKING,
    FAILED_CREATE_BOOKING,
    BOOKING_CREATED,
    // update statuses
    UPDATE_BOOKING,
    FAILED_UPDATE_BOOKING,
    BOOKING_UPDATED,
    // remove statuses
    REMOVE_BOOKING,
    BOOKING_REMOVED
}
