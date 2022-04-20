package com.reservoir.datareservoir.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class RocketDataInput {

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
    @NotNull
    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampBase;
}
