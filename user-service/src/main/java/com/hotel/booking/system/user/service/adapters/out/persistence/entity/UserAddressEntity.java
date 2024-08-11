package com.hotel.booking.system.user.service.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
@Entity
public class UserAddressEntity {

    @Id
    private UUID id;
    private String country;
    private String region;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
}
