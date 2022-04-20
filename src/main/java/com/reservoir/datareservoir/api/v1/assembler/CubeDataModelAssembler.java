package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.model.CubeDataModel;
import com.reservoir.datareservoir.domain.model.CubeData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CubeDataModelAssembler {

    private final ModelMapper modelMapper;

    public CubeDataModel toModel(CubeData cubeData) {
        return modelMapper.map(cubeData, CubeDataModel.class);
    }

    public List<CubeDataModel> toCollectionModel(List<CubeData> cubeDataList) {
        return cubeDataList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
