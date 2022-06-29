package com.reservoir.datareservoir.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.reservoir.datareservoir.api.v1.assembler.CubeDataDisassembler;
import com.reservoir.datareservoir.api.v1.assembler.CubeDataModelAssembler;
import com.reservoir.datareservoir.api.v1.domain.filter.PropertiesFilter;
import com.reservoir.datareservoir.api.v1.domain.model.CubeData;
import com.reservoir.datareservoir.api.v1.domain.service.CubeDataServiceApi;
import com.reservoir.datareservoir.api.v1.model.CubeDataModel;
import com.reservoir.datareservoir.api.v1.model.input.CubeDataInput;
import com.reservoir.datareservoir.api.v1.openapi.CubeDataControllerOpenApi;
import com.reservoir.datareservoir.core.security.ReservoirSecurity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/cube-data")
public class CubeDataController implements CubeDataControllerOpenApi {

    private final CubeDataServiceApi cubeDataService;
    private final CubeDataModelAssembler cubeDataModelAssembler;
    private final CubeDataDisassembler cubeDataDisassembler;
    private final ReservoirSecurity reservoirSecurity;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CubeDataModel>> getCubeData(PropertiesFilter propertiesFilter, ServletWebRequest request) {
    	ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
    	
    	String eTag = "0";
    	
    	OffsetDateTime lastDate = cubeDataService.getLastModifiedDate(propertiesFilter, reservoirSecurity.getAuthorities());
    	
    	if (lastDate != null) {
    		eTag = String.valueOf(lastDate.toEpochSecond());
    	}

    	if(request.checkNotModified(eTag)) {
    		return null;
    	}
    	
        List<CubeData> cubeDataList = cubeDataService.getAll(propertiesFilter, reservoirSecurity.getAuthorities());
        return ResponseEntity.ok()
        		.cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
        		.eTag(eTag)
        		.body(cubeDataModelAssembler.toCollectionModel(cubeDataList));
    }

    @Override
    @GetMapping(path = "{cubeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CubeDataModel getSingleCubeData(@PathVariable Long cubeId) throws AccessDeniedException {
        CubeData cubeData = cubeDataService.getOne(cubeId, reservoirSecurity.getAuthorities());
        return cubeDataModelAssembler.toModel(cubeData);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postCubeData(@RequestBody @Valid CubeDataInput cubeDataInput) {
        cubeDataService.save(cubeDataDisassembler.toDomainObject(cubeDataInput, reservoirSecurity.getClientId()));
    }

    @Override
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCubeData(PropertiesFilter propertiesFilter) {
        cubeDataService.deleteMulti(propertiesFilter, reservoirSecurity.getAuthorities());
    }

    @Override
    @DeleteMapping(path = "{cubeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSingleCubeData(@PathVariable Long cubeId) {
        cubeDataService.deleteOne(cubeId);
    }
}
