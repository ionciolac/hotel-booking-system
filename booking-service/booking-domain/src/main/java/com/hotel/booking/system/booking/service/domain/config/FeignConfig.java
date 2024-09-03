package com.hotel.booking.system.booking.service.domain.config;

import com.hotel.booking.system.booking.service.domain.client.ClientExceptionHandling;
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
