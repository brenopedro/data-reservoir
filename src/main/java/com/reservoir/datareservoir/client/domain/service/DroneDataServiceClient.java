package com.reservoir.datareservoir.client.domain.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.supercsv.io.ICsvBeanWriter;

import com.reservoir.datareservoir.client.config.auth.AuthorizationToken;
import com.reservoir.datareservoir.client.domain.model.CubeData;
import com.reservoir.datareservoir.client.domain.model.DroneData;
import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.properties.UrlProperties;
import com.reservoir.datareservoir.client.infrastructure.util.DroneServiceCacheUtil;

@Component
public class DroneDataServiceClient {
	
	@Autowired
	private AuthorizationToken authorizationToken;
	
	@Autowired
	private UrlProperties urlProperties;
	
	@Autowired
	private DroneServiceCacheUtil droneServiceCacheUtil;
	
	private String etag = null;
	private DroneData[] droneData;

    public DroneData[] getDroneData(PropertiesFilter propertiesFilter) {
        String accessToken = authorizationToken.getAccessToken(false);
        
        Map<String, String> params = new HashMap<>();
        params.put("fromTimeStamp", propertiesFilter.getFromTimeStamp());
        params.put("toTimeStamp", propertiesFilter.getToTimeStamp());
        
        String uri = UriComponentsBuilder.fromHttpUrl(urlProperties.getDroneData())
                .queryParam("fromTimeStamp", "{fromTimeStamp}")
                .queryParam("toTimeStamp", "{toTimeStamp}")
                .encode().toUriString();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        headers.setETag(etag);
        headers.setIfNoneMatch(etag);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DroneData[]> responseEntity = null;
        
        try {
            var request = new HttpEntity(headers);
            droneData = getBody(restTemplate, uri, request, params);
        } catch (HttpClientErrorException e) {
            accessToken = authorizationToken.getAccessToken(true);
            headers.setBearerAuth(accessToken);

            var request = new HttpEntity(headers);

            droneData = getBody(restTemplate, uri, request, params);
        }

        return droneData;
    }

	public void downloadCsv(ICsvBeanWriter csvBeanWriter, DroneData[] droneDataList) throws IOException {
		String[]  header = {"ID", "Altitude", "Battery Current", "Battery Voltage",
				"Position GPS X", "Position GPS Y", "Postion GPS Z", 
				"Time Stamp Drone", "Time Stamp Base"};
		
		String[] fieldMapping = {"id", "batteryCurrent", "batteryVoltage", 
				"positionGpsX", "positionGpsY", "positionGpsZ", 
				"timeStampDrone", "timeStampBase"};
		
		csvBeanWriter.writeHeader(header);
		
		for (DroneData droneData: droneDataList) {
			csvBeanWriter.write(droneData, fieldMapping);
		}
	}
	
	private DroneData[] getBody (RestTemplate restTemplate, String uri, HttpEntity request, 
			Map<String, String> params) {
		ResponseEntity<DroneData[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                request, DroneData[].class, params);
		
		DroneData[] droneData;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	droneData = droneServiceCacheUtil.cacheResult(true, responseEntity);
		} else {
			droneData = droneServiceCacheUtil.cacheResult(false, responseEntity);
		}
        etag = responseEntity.getHeaders().getETag();
        
        return droneData;
	}
}
