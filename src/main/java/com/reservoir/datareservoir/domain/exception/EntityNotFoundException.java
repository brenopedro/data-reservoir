package com.reservoir.datareservoir.domain.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    EntityNotFoundException(String message) {
        super(message);
    }

    EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
