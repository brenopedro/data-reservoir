package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.model.DroneDataModel;
import com.reservoir.datareservoir.domain.model.DroneData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DroneDataModelAssembler {

    private final ModelMapper modelMapper;

    public DroneDataModel toModel(DroneData droneData) {
        return modelMapper.map(droneData, DroneDataModel.class);
    }

    public List<DroneDataModel> toCollectionModel(List<DroneData> droneDataList) {
        return droneDataList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
