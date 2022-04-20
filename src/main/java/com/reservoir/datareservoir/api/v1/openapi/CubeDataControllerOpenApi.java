package com.reservoir.datareservoir.api.v1.openapi;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.reservoir.datareservoir.api.v1.exceptionhandler.Problem;
import com.reservoir.datareservoir.api.v1.model.CubeDataModel;
import com.reservoir.datareservoir.api.v1.model.input.CubeDataInput;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cube")
public interface CubeDataControllerOpenApi {

    @ApiOperation("Get all cube data for logged user between de time stamp")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    List<CubeDataModel> getCubeData(PropertiesFilter propertiesFilter);

    @ApiOperation("Get a single cube data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Cube ID invalid", response = Problem.class),
            @ApiResponse(code = 404, message = "Cube data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    CubeDataModel getSingleCubeData(@ApiParam(value = "Cube data ID", example = "1", required = true) Long cubeId) throws AccessDeniedException;

    @ApiOperation("Register a new input data")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 415, message = "Unsupported media type", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void postCubeData(@ApiParam(name = "body", value = "Cube representation model", required = true)
                              CubeDataInput cubeDataInput);

    @ApiOperation("Delete a list of data between the time stamps")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteCubeData(PropertiesFilter propertiesFilter);

    @ApiOperation("Delete a single cube data")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cube ID invalid", response = Problem.class),
            @ApiResponse(code = 400, message = "Invalid request", response = Problem.class),
            @ApiResponse(code = 404, message = "Cube data not found", response = Problem.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Problem.class)
    })
    void deleteSingleCubeData(@ApiParam(value = "Cube data ID", example = "1", required = true) Long cubeId);
}
