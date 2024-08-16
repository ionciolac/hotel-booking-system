package com.hotel.booking.system.user.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.persistence.entity.AddressEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
@Entity
public class UserAddressEntity extends AddressEntity {

    @Id
    private UUID id;
}
