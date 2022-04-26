package com.reservoir.datareservoir.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reservoir.datareservoir.api.v1.assembler.RocketDataDisassembler;
import com.reservoir.datareservoir.api.v1.assembler.RocketDataModelAssembler;
import com.reservoir.datareservoir.api.v1.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.api.v1.domain.model.RocketData;
import com.reservoir.datareservoir.api.v1.domain.service.RocketDataServiceApi;
import com.reservoir.datareservoir.api.v1.model.RocketDataModel;
import com.reservoir.datareservoir.api.v1.model.input.RocketDataInput;
import com.reservoir.datareservoir.api.v1.openapi.RocketDataControllerOpenApi;
import com.reservoir.datareservoir.core.security.ReservoirSecurity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/rocket-data")
public class RocketDataController implements RocketDataControllerOpenApi {

    private final RocketDataServiceApi rocketDataService;
    private final RocketDataModelAssembler rocketDataModelAssembler;
    private final RocketDataDisassembler rocketDataDisassembler;
    private final ReservoirSecurity reservoirSecurity;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RocketDataModel> getRocketData(PropertiesFilter propertiesFilter) {
        List<RocketData> rocketDataList = rocketDataService.getAll(propertiesFilter, reservoirSecurity.getAuthorities());
        return rocketDataModelAssembler.toCollectionModel(rocketDataList);
    }

    @Override
    @GetMapping(path = "{rocketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RocketDataModel getSingleRocketData(@PathVariable Long rocketId) throws AccessDeniedException {
        RocketData rocketData = rocketDataService.getOne(rocketId, reservoirSecurity.getAuthorities());
        return rocketDataModelAssembler.toModel(rocketData);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postRocketData(@RequestBody @Valid RocketDataInput rocketDataInput) {
        rocketDataService.save(rocketDataDisassembler.toDomainObject(rocketDataInput, reservoirSecurity.getClientId()));
    }

    @Override
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRocketData(PropertiesFilter propertiesFilter) {
        rocketDataService.deleteMulti(propertiesFilter, reservoirSecurity.getAuthorities());
    }

    @Override
    @DeleteMapping(path = "{rocketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSingleRocketData(@PathVariable Long rocketId) {
        rocketDataService.deleteOne(rocketId);
    }
}
