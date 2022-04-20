package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.model.input.DroneDataInput;
import com.reservoir.datareservoir.domain.model.DroneData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DroneDataDisassembler {

    private final ModelMapper modelMapper;

    public DroneData toDomainObject(DroneDataInput droneDataInput, String user) {
        DroneData droneData = modelMapper.map(droneDataInput, DroneData.class);
        droneData.setUser(user);
        return droneData;
    }
}
