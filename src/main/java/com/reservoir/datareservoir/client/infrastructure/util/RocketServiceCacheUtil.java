package com.reservoir.datareservoir.client.infrastructure.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.reservoir.datareservoir.client.domain.model.RocketData;

@Component
public class RocketServiceCacheUtil {

	@Cacheable(value = "rocketCache", condition = "!#cache", key = "'rocket'")
	@CachePut(value = "rocketCache", condition = "#cache", key = "'rocket'")
	public RocketData[] cacheResult(boolean cache, ResponseEntity<RocketData[]> responseEntity) {
		return  responseEntity.getBody();
	}
}
