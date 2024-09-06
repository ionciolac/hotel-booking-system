package com.hotel.booking.system.payment.domain.model;

import com.hotel.booking.system.common.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private UUID id;
    private UUID bookingId;
    private UUID roomBookingId;
    private double forRoomAmount;
    private String roomCurrency;
    private UUID customerId;
    private double inCustomerAmount;
    private String customerCurrency;
    private PaymentStatus status;
}
