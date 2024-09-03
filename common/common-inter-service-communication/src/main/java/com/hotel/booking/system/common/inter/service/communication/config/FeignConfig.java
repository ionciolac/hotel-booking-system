package com.hotel.booking.system.common.inter.service.communication.config;

import com.hotel.booking.system.common.inter.service.communication.exception.handiling.ClientExceptionHandling;
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
