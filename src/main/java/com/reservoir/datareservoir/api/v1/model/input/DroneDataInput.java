package com.reservoir.datareservoir.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class DroneDataInput {

    @ApiModelProperty(example = "1")
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
    @NotNull
    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime timeStampBase;
}
