package com.epam.ld.module2.testing.exception;

public class MailFileException extends RuntimeException {
    private final String message;

    public MailFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
