package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.domain.model.CubeData;
import com.reservoir.datareservoir.api.v1.model.input.CubeDataInput;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CubeDataDisassembler {

    private final ModelMapper modelMapper;

    public CubeData toDomainObject(CubeDataInput cubeDataInput, String user) {
        CubeData cubeData = modelMapper.map(cubeDataInput, CubeData.class);
        cubeData.setUser(user);
        return cubeData;
    }
}
