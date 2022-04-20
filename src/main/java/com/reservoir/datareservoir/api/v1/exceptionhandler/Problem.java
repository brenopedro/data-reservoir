package com.reservoir.datareservoir.api.v1.exceptionhandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problem")
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "2019-12-01T18:09:02.70844Z")
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://datareservoir.com.br/invalid-data")
    private String type;

    @ApiModelProperty(example = "Invalid data")
    private String title;
    private String detail;
    private String userMessage;

    @ApiModelProperty("Objects list or fields that generated the error (optional)")
    private List<Object> objects;

    @ApiModel("ProblemObject")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "timeStampBase")
        private String name;

        @ApiModelProperty(example = "TimeStamp cannot be null")
        private String userMessage;
    }
}
