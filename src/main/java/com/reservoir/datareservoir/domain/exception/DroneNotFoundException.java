package com.reservoir.datareservoir.domain.exception;

public class DroneNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public DroneNotFoundException(String message) {
        super(message);
    }

    public DroneNotFoundException(Long droneId) {
        this(String.format("It doesn't exist a drone data with id %d", droneId));
    }
}
