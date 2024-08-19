package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

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
@Table(name = "room_bookings")
@Entity
public class RoomBookingEntity extends EntityAuditing {

    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rooms_id", referencedColumnName = "id")
    private RoomEntity room;
    private UUID userId;
    private UUID bookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Long nightsNumber;
    private Double pricePerNight;
    private Double totalPrice;
    private String currency;
}
