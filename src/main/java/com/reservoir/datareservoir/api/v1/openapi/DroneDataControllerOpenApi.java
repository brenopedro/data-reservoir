package com.reservoir.datareservoir.api.v1.openapi;

import com.reservoir.datareservoir.api.v1.exceptionhandler.Problem;
import com.reservoir.datareservoir.api.v1.model.DroneDataModel;
import com.reservoir.datareservoir.api.v1.model.input.DroneDataInput;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Drone")
public interface DroneDataControllerOpenApi {

    @ApiOperation("Get all drone data for logged user between de time stamp")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    List<DroneDataModel> getDroneData(PropertiesFilter propertiesFilter);

    @ApiOperation("Get a single drone data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Drone ID invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Drone data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    DroneDataModel getSingleDroneData(@ApiParam(value = "Cube data ID", example = "1", required = true) Long droneId);

    @ApiOperation("Register a new input data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 415, message = "Unsupported media type", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void postDroneData(@ApiParam(name = "body", value = "Drone representation model", required = true)
                               DroneDataInput droneDataInput);

    @ApiOperation("Delete a list of data between the time stamps")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteDroneData(PropertiesFilter propertiesFilter);

    @ApiOperation("Delete a single drone data")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Drone ID invalid", response = Problem.class),
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 404, message = "Drone data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteSingleDroneData(@ApiParam(value = "Drone data ID", example = "1", required = true) Long droneId);
}
