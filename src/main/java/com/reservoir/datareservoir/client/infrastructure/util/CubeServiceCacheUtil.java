package com.reservoir.datareservoir.client.infrastructure.util;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.reservoir.datareservoir.client.domain.model.CubeData;

@Component
public class CubeServiceCacheUtil {

	@Cacheable(value = "cubeCache", condition = "!#cache", key = "'cube'")
	@CachePut(value = "cubeCache", condition = "#cache", key = "'cube'")
	public CubeData[] cacheResult(boolean cache, ResponseEntity<CubeData[]> responseEntity) {
		return  responseEntity.getBody();
	}
}
