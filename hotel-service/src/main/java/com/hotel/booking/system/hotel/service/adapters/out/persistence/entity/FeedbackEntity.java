package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.persistence.EntityAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
@Entity
public class FeedbackEntity extends EntityAuditing {

    @Id
    private UUID id;
    @ManyToOne
    private HotelEntity hotel;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "user_fullname")
    private String userFullName;
    @Column(name = "user_message")
    private String userMessage;
    @Column(name = "user_mark")
    private double userMark;
}
