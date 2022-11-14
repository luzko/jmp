package com.epam.jmp.spring.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AlreadyExistsException extends RuntimeException {
    private final String message;
}
