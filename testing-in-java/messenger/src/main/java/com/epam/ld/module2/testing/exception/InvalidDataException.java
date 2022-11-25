package com.epam.ld.module2.testing.exception;

public class InvalidDataException extends RuntimeException {
    private String message;

    public InvalidDataException() {

    }

    public InvalidDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
