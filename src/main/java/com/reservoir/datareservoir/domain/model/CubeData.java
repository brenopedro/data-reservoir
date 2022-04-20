package com.reservoir.datareservoir.domain.model;

import com.reservoir.datareservoir.domain.modelinterface.Entities;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
public class CubeData extends Entities {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
