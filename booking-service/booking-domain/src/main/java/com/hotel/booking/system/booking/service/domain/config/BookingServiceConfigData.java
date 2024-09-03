package com.hotel.booking.system.booking.service.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "booking-service")
@Configuration
public class BookingServiceConfigData {

    private String hotelServiceAddress;
    private String hotelServicePort;
    private String createBookingTopicName;
    private String createdBookingTopicName;
    private String updateBookingTopicName;
    private String updatedBookingTopicName;
    private String removeBookingTopicName;
    private String removedBookingTopicName;
}
