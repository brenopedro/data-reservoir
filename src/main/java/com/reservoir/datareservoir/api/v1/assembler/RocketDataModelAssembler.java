package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.model.RocketDataModel;
import com.reservoir.datareservoir.domain.model.RocketData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RocketDataModelAssembler {

    private final ModelMapper modelMapper;

    public RocketDataModel toModel(RocketData rocketData) {
        return modelMapper.map(rocketData, RocketDataModel.class);
    }

    public List<RocketDataModel> toCollectionModel(List<RocketData> rocketDataList) {
        return rocketDataList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
