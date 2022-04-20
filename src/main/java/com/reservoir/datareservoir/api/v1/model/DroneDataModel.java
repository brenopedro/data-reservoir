package com.reservoir.datareservoir.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class DroneDataModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1.11")
    private BigDecimal altitude;

    @ApiModelProperty(example = "1.11")
    private BigDecimal batteryCurrent;
    @ApiModelProperty(example = "1.11")
    private BigDecimal batteryVoltage;

    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsZ;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampDrone;
    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampBase;

    @ApiModelProperty(example = "base-station")
    private String user;
}
