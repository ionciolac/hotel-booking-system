package com.hotel.booking.system.common.domain;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
