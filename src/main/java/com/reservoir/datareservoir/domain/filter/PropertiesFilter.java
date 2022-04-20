package com.reservoir.datareservoir.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Setter
@Getter
public class PropertiesFilter {

    @ApiModelProperty(example = "cube-station", value = "User of a base station")
    private String user;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z", value = "Start time stamp to filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime fromTimeStamp;

    @ApiModelProperty(example = "2019-12-01T20:34:04Z", value = "End time stamp to filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime toTimeStamp;

}
