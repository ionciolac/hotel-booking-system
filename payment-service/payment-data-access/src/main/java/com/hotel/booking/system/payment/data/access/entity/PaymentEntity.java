package com.hotel.booking.system.payment.data.access.entity;

import com.hotel.booking.system.common.common.enums.PaymentStatus;
import com.hotel.booking.system.common.data.access.EntityAuditing;
import jakarta.persistence.*;
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
@Table(name = "payments")
@Entity
public class PaymentEntity extends EntityAuditing {

    @Id
    private UUID id;
    private UUID bookingId;
    private UUID roomBookingId;
    private double forRoomAmount;
    private String roomCurrency;
    private UUID customerId;
    private double inCustomerAmount;
    private String customerCurrency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
