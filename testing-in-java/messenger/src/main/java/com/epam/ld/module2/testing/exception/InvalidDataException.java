package com.epam.ld.module2.testing.exception;

public class InvalidDataException extends RuntimeException {
    private final String message;

    public InvalidDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
