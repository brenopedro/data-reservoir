package com.reservoir.datareservoir;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.reservoir.datareservoir.core.io.Base64ProtocolResolver;

@EnableCaching
@SpringBootApplication
public class DataReservoirApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		SpringApplication app = new SpringApplication(DataReservoirApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
