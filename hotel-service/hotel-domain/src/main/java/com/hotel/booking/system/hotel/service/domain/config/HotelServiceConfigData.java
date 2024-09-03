package com.hotel.booking.system.hotel.service.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "hotel-service")
@Configuration
public class HotelServiceConfigData {

    private String createBookingTopicName;
    private String createdBookingTopicName;
    private String updateBookingTopicName;
    private String updatedBookingTopicName;
    private String removeBookingTopicName;
    private String removedBookingTopicName;
}
