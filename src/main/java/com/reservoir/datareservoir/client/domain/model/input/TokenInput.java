package com.reservoir.datareservoir.client.domain.model.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenInput {

    private String grant_type;
    private String username;
    private String password;
}
