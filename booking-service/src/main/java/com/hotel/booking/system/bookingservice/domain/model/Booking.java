package com.hotel.booking.system.bookingservice.domain.model;

import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.common.domain.model.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseId {

    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private UUID roomBookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private BookingStatus status;
}
