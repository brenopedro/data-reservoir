package com.reservoir.datareservoir.api.v1.controller;

import com.reservoir.datareservoir.api.v1.assembler.DroneDataDisassembler;
import com.reservoir.datareservoir.api.v1.assembler.DroneDataModelAssembler;
import com.reservoir.datareservoir.api.v1.model.DroneDataModel;
import com.reservoir.datareservoir.api.v1.model.input.DroneDataInput;
import com.reservoir.datareservoir.api.v1.openapi.DroneDataControllerOpenApi;
import com.reservoir.datareservoir.core.security.ReservoirSecurity;
import com.reservoir.datareservoir.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.domain.model.DroneData;
import com.reservoir.datareservoir.domain.service.DroneDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/drone-data")
public class DroneDataController implements DroneDataControllerOpenApi {

    private final DroneDataService droneDataService;
    private final DroneDataModelAssembler droneDataModelAssembler;
    private final DroneDataDisassembler droneDataDisassembler;
    private final ReservoirSecurity reservoirSecurity;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DroneDataModel> getDroneData(PropertiesFilter propertiesFilter) {
        List<DroneData> droneDataList = droneDataService.getAll(propertiesFilter, reservoirSecurity.getAuthorities());
        return droneDataModelAssembler.toCollectionModel(droneDataList);
    }

    @Override
    @GetMapping(path = "{droneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DroneDataModel getSingleDroneData(@PathVariable Long droneId) throws AccessDeniedException {
        DroneData droneData = droneDataService.getOne(droneId, reservoirSecurity.getAuthorities());
        return droneDataModelAssembler.toModel(droneData);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postDroneData(@RequestBody @Valid DroneDataInput droneDataInput) {
        droneDataService.save(droneDataDisassembler.toDomainObject(droneDataInput, reservoirSecurity.getClientId()));
    }

    @Override
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDroneData(PropertiesFilter propertiesFilter) {
        droneDataService.deleteMulti(propertiesFilter, reservoirSecurity.getAuthorities());
    }

    @Override
    @DeleteMapping(path = "{droneId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSingleDroneData(@PathVariable Long droneId) {
        droneDataService.deleteOne(droneId);
    }
}
