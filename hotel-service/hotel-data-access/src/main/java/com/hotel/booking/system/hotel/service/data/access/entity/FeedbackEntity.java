package com.hotel.booking.system.hotel.service.data.access.entity;

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
@Table(name = "feedbacks")
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
