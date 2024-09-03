package com.hotel.booking.system.common.application;

import com.hotel.booking.system.common.application.data.res.ExceptionResponse;
import com.hotel.booking.system.common.common.exception.AlreadyExistException;
import com.hotel.booking.system.common.common.exception.BadRequestException;
import com.hotel.booking.system.common.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        var exceptionResponse = ExceptionResponse.builder().date(Instant.now()).message("Unexpected error!").build();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ResponseBody
    @ExceptionHandler(value = {AlreadyExistException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleAlreadyExistException(AlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        var exceptionResponse = ExceptionResponse.builder().date(Instant.now()).message(ex.getMessage()).build();
        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse);
    }

    @ResponseBody
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage(), ex);
        var exceptionResponse = ExceptionResponse.builder().date(Instant.now()).message(ex.getMessage()).build();
        return ResponseEntity.status(NOT_FOUND).body(exceptionResponse);
    }

    @ResponseBody
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        var exceptionResponse = ExceptionResponse.builder().date(Instant.now()).message(ex.getMessage()).build();
        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse);
    }
}
