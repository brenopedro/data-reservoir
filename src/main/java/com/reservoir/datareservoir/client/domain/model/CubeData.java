package com.reservoir.datareservoir.client.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CubeData {

    private Long id;

    private BigDecimal externalTemperature;

    private BigDecimal batteryCurrent;
    private BigDecimal batteryVoltage;
    private BigDecimal batteryTemperature;

    private BigDecimal magneticFieldX;
    private BigDecimal magneticFieldY;
    private BigDecimal magneticFieldZ;

    private BigDecimal eulerAngleX;
    private BigDecimal eulerAngleY;
    private BigDecimal eulerAngleZ;

    private BigDecimal linearSpeedX;
    private BigDecimal linearSpeedY;
    private BigDecimal linearSpeedZ;

    private BigDecimal angularSpeedX;
    private BigDecimal angularSpeedY;
    private BigDecimal angularSpeedZ;

    private BigDecimal transmittedTransceiverPower;
    private BigDecimal receivedTransceiverPower;

    private OffsetDateTime timeStampCube;
    private OffsetDateTime timeStampBase;

    private String user;
}
