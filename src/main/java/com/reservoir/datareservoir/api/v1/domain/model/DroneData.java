package com.reservoir.datareservoir.api.v1.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.reservoir.datareservoir.api.v1.domain.modelinterface.Entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
public class DroneData extends Entities {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private BigDecimal altitude;

    private BigDecimal batteryCurrent;
    private BigDecimal batteryVoltage;

    private BigDecimal positionGpsX;
    private BigDecimal positionGpsY;
    private BigDecimal positionGpsZ;

    private OffsetDateTime timeStampDrone;
    private OffsetDateTime timeStampBase;

    private String user;
}
