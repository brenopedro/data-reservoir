package com.reservoir.datareservoir.client.config.properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Component
@Setter
@Getter
@ConfigurationProperties("datareservoir.authorization")
public class AuthorizationProperties {
	
	@NotNull
	private String authUsername;
	
	@NotNull
	private String authPassword;
	
	@NotNull
	private String authClientUsername;
	
	@NotNull
	private String authClientPassword;

}
