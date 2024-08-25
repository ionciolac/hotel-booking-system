package com.hotel.booking.system.bookingservice.config;

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
    private String bookRoomTopicName;
    private String roomBookedTopicName;
    private String removeBookingTopicName;
    private String removedBookingTopicName;
}
