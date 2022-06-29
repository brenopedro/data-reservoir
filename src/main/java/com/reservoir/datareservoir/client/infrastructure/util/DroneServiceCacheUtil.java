package com.reservoir.datareservoir.client.infrastructure.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.reservoir.datareservoir.client.domain.model.DroneData;

@Component
public class DroneServiceCacheUtil {

	@Cacheable(value = "droneCache", condition = "!#cache", key = "'drone'")
	@CachePut(value = "droneCache", condition = "#cache", key = "'drone'")
	public DroneData[] cacheResult(boolean cache, ResponseEntity<DroneData[]> responseEntity) {
		return  responseEntity.getBody();
	}
}
