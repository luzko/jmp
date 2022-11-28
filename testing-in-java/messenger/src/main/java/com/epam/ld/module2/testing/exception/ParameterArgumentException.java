package com.epam.ld.module2.testing.exception;

public class ParameterArgumentException extends RuntimeException {
    private String message;

    public ParameterArgumentException() {

    }

    public ParameterArgumentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
