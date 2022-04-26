package com.reservoir.datareservoir.client.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Component
@Setter
@Getter
@ConfigurationProperties("datareservoir.security")
public class SecurityProperties {

    @NotNull
    private String user;

    @NotNull
    private String password;
}
