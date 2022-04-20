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
public class RocketData extends Entities {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
