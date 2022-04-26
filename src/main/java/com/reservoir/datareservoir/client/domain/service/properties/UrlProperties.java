package com.reservoir.datareservoir.client.domain.service.properties;

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
@ConfigurationProperties("datareservoir.url")
public class UrlProperties {
	
	@NotNull
	private String cubeData;
	
	@NotNull
	private String droneData;
	
	@NotNull
	private String rocketData;
	
	@NotNull
	private String authorizationUrl;

}
