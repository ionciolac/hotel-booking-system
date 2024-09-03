package com.hotel.booking.system.hotel.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan(basePackages = "com.hotel.booking.system.hotel.service.data.access.entity")
@EnableJpaRepositories(basePackages = "com.hotel.booking.system.hotel.service.data.access.repository")
@SpringBootApplication(scanBasePackages = "com.hotel.booking.system")
public class HotelContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelContainerApplication.class, args);
    }
}
