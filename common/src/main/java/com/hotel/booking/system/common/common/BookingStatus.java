package com.hotel.booking.system.common.common;

import lombok.Getter;

@Getter
public enum BookingStatus {

    RESERVED, //when user create a booking in booking system
    BOOKING_ROOM, //when user init payment
    ROOM_IS_ALREADY_BOOKED, //when user init process of payment and room is already booked from date to date - from this status booking cannot be changed to another only to delete
    ROOM_BOOKED, //when room was reserved hotel service change status to this status and init process of payment
    WAITING_PAYMENT, //when room was booked in hotel service and booking service init payment
    PAYMENT_REFUSED, //when payment service response that something is wrong and payment service refused to payment then remove room booking in hotel service
    PAYED, //when payment service response that everything is good with payment then booking system can change status to BOOKED
    BOOKED, //when hotel service booked room and notify booking system that room was booked
    INIT_CANCEL_BOOKING, //when user init to cancel booking
    BOOKED_CANCELED //when user canceled booking with 1 day before check_in
}
