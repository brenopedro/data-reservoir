package com.reservoir.datareservoir.client.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class RocketData {

    private Long id;

    private BigDecimal altitude;
    private BigDecimal externalTemperature;
    private BigDecimal acceleration;

    private BigDecimal eulerAngleX;
    private BigDecimal eulerAngleY;
    private BigDecimal eulerAngleZ;

    private BigDecimal positionGpsX;
    private BigDecimal positionGpsY;
    private BigDecimal positionGpsZ;

    private BigDecimal magneticFieldX;
    private BigDecimal magneticFieldY;
    private BigDecimal magneticFieldZ;

    private BigDecimal linearSpeedX;
    private BigDecimal linearSpeedY;
    private BigDecimal linearSpeedZ;

    private BigDecimal angularSpeedX;
    private BigDecimal angularSpeedY;
    private BigDecimal angularSpeedZ;

    private OffsetDateTime timeStampRocket;
    private OffsetDateTime timeStampBase;

    private String user;
}
