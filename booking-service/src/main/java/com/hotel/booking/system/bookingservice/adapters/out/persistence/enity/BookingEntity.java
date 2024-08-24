package com.hotel.booking.system.bookingservice.adapters.out.persistence.enity;

import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
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
@Table(name = "booking")
@Entity
public class BookingEntity extends EntityAuditing {

    @Id
    private UUID id;
    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private UUID roomBookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
