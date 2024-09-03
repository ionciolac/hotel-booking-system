package com.hotel.booking.system.booking.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients(basePackages = "com.hotel.booking.system.booking.service.domain.client")
@EnableJpaAuditing
@EntityScan(basePackages = "com.hotel.booking.system.booking.service.data.access.entity")
@EnableJpaRepositories(basePackages = "com.hotel.booking.system.booking.service.data.access.repository")
@SpringBootApplication(scanBasePackages = "com.hotel.booking.system")
public class BookingContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingContainerApplication.class, args);
    }
}
