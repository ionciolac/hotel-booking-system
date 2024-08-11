package com.hotel.booking.system.user.service.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
@Entity
public class UserEntity {

    @Id
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id", referencedColumnName = "id")
    private UserAddressEntity address;
}
