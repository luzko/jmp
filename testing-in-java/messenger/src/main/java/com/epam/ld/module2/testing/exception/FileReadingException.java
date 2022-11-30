package com.epam.ld.module2.testing.exception;

public class FileReadingException extends RuntimeException {
    private final String message;

    public FileReadingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
