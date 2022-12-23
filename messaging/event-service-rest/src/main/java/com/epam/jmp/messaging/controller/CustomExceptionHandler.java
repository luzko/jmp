package com.epam.jmp.messaging.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.jmp.messaging.dto.Error;
import com.epam.jmp.messaging.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            ServletRequestBindingException.class,
            HttpMessageNotReadableException.class})
    public Error apiExceptionHandler(final Exception ex) {
        logException(ex);
        return Error.builder()
                .code("BAD_REQUEST")
                .message(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Error notFoundExceptionHandler(final Exception ex) {
        logException(ex);
        return Error.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error unexpectedErrorExceptionHandler(final Exception ex) {
        logException(ex);
        return Error.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message(ex.getMessage())
                .build();
    }

    private void logException(Throwable cause) {
        log.error("Error!", cause);
    }
}
