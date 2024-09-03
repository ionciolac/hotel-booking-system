package com.hotel.booking.system.booking.service.domain.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hotel.booking.system.common.application.data.res.ExceptionResponse;
import com.hotel.booking.system.common.common.exception.BadRequestException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class ClientExceptionHandling implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ExceptionResponse exceptionResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {
            exceptionResponse = mapper.readValue(bodyIs, ExceptionResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 400 -> throw new BadRequestException(exceptionResponse.getMessage());
            case 404 -> throw new NotFoundException(exceptionResponse.getMessage());
            default -> {
                return new Exception("Generic error");
            }
        }
    }
}
