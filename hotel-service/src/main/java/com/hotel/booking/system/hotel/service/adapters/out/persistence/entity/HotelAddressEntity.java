package com.hotel.booking.system.hotel.service.adapters.out.persistence.entity;

import com.hotel.booking.system.common.persistence.entity.AddressEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_address")
@Entity
public class HotelAddressEntity extends AddressEntity {

    @Id
    private UUID id;
}
