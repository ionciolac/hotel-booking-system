package com.hotel.booking.system.hotel.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "hotel-service")
@Configuration
public class HotelServiceConfigData {

    private String bookRoomTopicName;
    private String roomBookedTopicName;
    private String removeBookingTopicName;
    private String removedBookingTopicName;
}
