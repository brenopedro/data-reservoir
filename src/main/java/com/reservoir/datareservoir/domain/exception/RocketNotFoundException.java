package com.reservoir.datareservoir.domain.exception;

public class RocketNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RocketNotFoundException(String message) {
        super(message);
    }

    public RocketNotFoundException(Long cubeId) {
        this(String.format("It doesn't exist a rocket data with id %d", cubeId));
    }
}
