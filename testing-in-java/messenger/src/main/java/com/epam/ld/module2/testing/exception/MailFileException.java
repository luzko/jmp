package com.epam.ld.module2.testing.exception;

public class MailFileException extends RuntimeException {
    private String message;

    public MailFileException() {

    }

    public MailFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
