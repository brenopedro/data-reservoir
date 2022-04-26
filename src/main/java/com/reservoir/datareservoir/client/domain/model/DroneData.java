package com.reservoir.datareservoir.client.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class DroneData {

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
