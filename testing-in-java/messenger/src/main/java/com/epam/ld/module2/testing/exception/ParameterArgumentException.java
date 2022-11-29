package com.epam.ld.module2.testing.exception;

public class ParameterArgumentException extends RuntimeException {
    private final String message;

    public ParameterArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
