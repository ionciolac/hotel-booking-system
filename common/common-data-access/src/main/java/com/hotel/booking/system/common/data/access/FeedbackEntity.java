package com.hotel.booking.system.common.data.access;

import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public abstract class FeedbackEntity extends EntityAuditing {

    private UUID customerId;
    private String customerFullName;
    private String customerMessage;
    private double customerMark;
}
