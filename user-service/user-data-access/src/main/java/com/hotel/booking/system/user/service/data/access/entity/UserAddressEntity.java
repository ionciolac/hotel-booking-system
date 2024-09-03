package com.hotel.booking.system.user.service.data.access.entity;

import com.hotel.booking.system.common.data.access.AddressEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

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
