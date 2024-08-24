package com.hotel.booking.system.common.domain.utils;

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
}
