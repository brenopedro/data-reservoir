package com.reservoir.datareservoir.domain.exception;

public class CubeNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CubeNotFoundException(String message) {
        super(message);
    }

    public CubeNotFoundException(Long cubeId) {
        this(String.format("It doesn't exist a cube data with id %d", cubeId));
    }
}
