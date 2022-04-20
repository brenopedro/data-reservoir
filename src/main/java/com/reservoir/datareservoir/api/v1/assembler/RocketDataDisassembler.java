package com.reservoir.datareservoir.api.v1.assembler;

import com.reservoir.datareservoir.api.v1.model.input.RocketDataInput;
import com.reservoir.datareservoir.domain.model.RocketData;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RocketDataDisassembler {

    private final ModelMapper modelMapper;

    public RocketData toDomainObject(RocketDataInput rocketDataInput, String user) {
        RocketData rocketData = modelMapper.map(rocketDataInput, RocketData.class);
        rocketData.setUser(user);
        return rocketData;
    }
}
