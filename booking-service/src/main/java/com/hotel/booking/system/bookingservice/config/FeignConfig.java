package com.hotel.booking.system.bookingservice.config;

import com.hotel.booking.system.bookingservice.domain.client.ClientExceptionHandling;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ClientExceptionHandling();
    }
}
