package com.hotel.booking.system.common.inter.service.communication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "inter-service-config")
@Configuration
public class FeignConfigData {

    private String hotelServiceAddress;
    private String hotelServicePort;
}
