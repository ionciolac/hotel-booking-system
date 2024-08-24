package com.hotel.booking.system.common.common;

public enum BookingStatus {

    RESERVED, //when user create a booking in booking system
    WAITING_PAYMENT, //when user init payment to book a room
    PAYMENT_REFUSED, //when payment service response that something is wrong and payment service refused to payment
    PAYED, //when payment service response that everything is good with payment and booking system can book room in hotel service
    BOOKED_REFUSED, //when hotel service refused to book room because it already was booked. init process to refund money
    BOOKED, //when hotel service booked room and notify booking system that room was booked
    BOOKED_CANCELED //when user canceled booking with 1 day before check_in
}
