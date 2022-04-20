package com.reservoir.datareservoir.api.v1.openapi;

import com.reservoir.datareservoir.api.v1.exceptionhandler.Problem;
import com.reservoir.datareservoir.api.v1.model.RocketDataModel;
import com.reservoir.datareservoir.api.v1.model.input.RocketDataInput;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Rocket")
public interface RocketDataControllerOpenApi {

    @ApiOperation("Get all rocket data for logged user between de time stamp")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    List<RocketDataModel> getRocketData(PropertiesFilter propertiesFilter);

    @ApiOperation("Get a single rocket data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Rocket ID invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Rocket data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    RocketDataModel getSingleRocketData(@ApiParam(value = "Rocket data ID", example = "1", required = true) Long rocketId);

    @ApiOperation("Register a new input data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 415, message = "Unsupported media type", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void postRocketData(@ApiParam(name = "body", value = "Rocket representation model", required = true)
                                RocketDataInput rocketDataInput);

    @ApiOperation("Delete a list of data between the time stamps")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteRocketData(PropertiesFilter propertiesFilter);

    @ApiOperation("Delete a single rocket data")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Rocket ID invalid", response = Problem.class),
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 404, message = "Rocket data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteSingleRocketData(@ApiParam(value = "Rocket data ID", example = "1", required = true) Long rocketId);
}
