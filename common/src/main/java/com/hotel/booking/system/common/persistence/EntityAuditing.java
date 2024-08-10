package com.hotel.booking.system.common.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class EntityAuditing {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdOn;
    @Column(nullable = false)
    @LastModifiedDate
    private Instant updatedOn;
}
