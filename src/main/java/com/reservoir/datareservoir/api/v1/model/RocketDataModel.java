package com.reservoir.datareservoir.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class RocketDataModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1.11")
    private BigDecimal altitude;
    @ApiModelProperty(example = "1.11")
    private BigDecimal externalTemperature;
    @ApiModelProperty(example = "1.11")
    private BigDecimal acceleration;

    @ApiModelProperty(example = "1.11")
    private BigDecimal eulerAngleX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal eulerAngleY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal eulerAngleZ;

    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal positionGpsZ;

    @ApiModelProperty(example = "1.11")
    private BigDecimal magneticFieldX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal magneticFieldY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal magneticFieldZ;

    @ApiModelProperty(example = "1.11")
    private BigDecimal linearSpeedX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal linearSpeedY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal linearSpeedZ;

    @ApiModelProperty(example = "1.11")
    private BigDecimal angularSpeedX;
    @ApiModelProperty(example = "1.11")
    private BigDecimal angularSpeedY;
    @ApiModelProperty(example = "1.11")
    private BigDecimal angularSpeedZ;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampRocket;
    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampBase;

    @ApiModelProperty(example = "base-station")
    private String user;
}
