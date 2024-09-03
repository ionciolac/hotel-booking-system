package com.hotel.booking.system.common.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppCommonMessages {

    public static final String HOTEL = "Hotel";
    public static final String USER = "User";
    public static final String ROOM = "Room";
    public static final String FEEDBACK = "Feedback";
    public static final String BOOKING = "Booking";

    // controller messages
    public static final String CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE = "%S with ID: %s was deleted";

    // services messages
    public static final String SERVICE_OBJECT_WAS_NOT_FOUND_IN_DB_MESSAGE = "%s was not found in DB by id: %s.";
    public static final String SERVICE_USER_ALREADY_EXIST_MESSAGE = "User with same username, phoneNumber and email already exist in DB.";
    public static final String SERVICE_HOTEL_ALREADY_EXIST_ON_ADDRESS_MESSAGE = "Hotel with name: %s with entered address already exist in DB.";
    public static final String SERVICE_ROOM_ALREADY_EXIST_MESSAGE = "Room with door number: %s already exist in hotel: %s";
    public static final String SERVICE_USER_ALREADY_ADDED_FEEDBACK_MESSAGE = "User with id: %s already added feedback to hotel with id %s";
    public static final String SERVICE_RESERVATION_DATE_VALIDATION_MESSAGE = "toDate param can't be smaller or equal to fromDate param";
    public static final String SERVICE_ROOM_ALREADY_IS_BOOKED_FROM_TO_DATE_MESSAGE = "Room with id: %s already is booked from date: %s to date %s.";
    public static final String SERVICE_BOOKING_IS_BOOKED_AND_CANNOT_BE_REMOVED_MESSAGE = "Booking is in booked and can't be removed. First cancel booking.";
    public static final String SERVICE_BOOKING_IS_ALREADY_EXIST_MESSAGE = "Booking room id: %s by user id: %s from date: %s to date: %s already exist.";
    public static final String SERVICE_BOOKING_MUST_BE_IN_RESERVED_STATUS_MESSAGE = "Booking %s must be in status %s to init payment.";
    public static final String SERVICE_BOOKING_CANNOT_BE_CANCELED_LESS_24H_BEFORE_CHECKIN_MESSAGE = "Booking can't be canceled because is less then 24h. Before check in.";
    public static final String SERVICE_BOOKING_CANNOT_BE_CANCELED_WRONG_STATUS_MESSAGE = "Only bookings in status %s can be canceled";
    public static final String SERVICE_CANNOT_UPDATE_BOOKING_MESSAGE = "Only in status %s or %s can update booking.";
    public static final String SERVICE_VALIDATE_IF_DATES_WAS_CHANGED_ON_UPDATE_MESSAGE = "Please ensure that at least one date was changed.";
}
