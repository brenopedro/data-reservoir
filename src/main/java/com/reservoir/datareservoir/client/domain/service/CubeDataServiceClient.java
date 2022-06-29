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
import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.properties.UrlProperties;
import com.reservoir.datareservoir.client.infrastructure.util.CubeServiceCacheUtil;

@Component
public class CubeDataServiceClient {

	@Autowired
	private AuthorizationToken authorizationToken;
	
	@Autowired
	private UrlProperties urlProperties;
	
	@Autowired
	private CubeServiceCacheUtil cubeServiceCacheUtil;
	
	private String etag = null;
	private CubeData[] cubeData;
	
    public CubeData[] getCubeData(PropertiesFilter propertiesFilter) {
        String accessToken = authorizationToken.getAccessToken(false);
        
        Map<String, String> params = new HashMap<>();
        params.put("fromTimeStamp", propertiesFilter.getFromTimeStamp());
        params.put("toTimeStamp", propertiesFilter.getToTimeStamp());

        String uri = UriComponentsBuilder.fromHttpUrl(urlProperties.getCubeData())
                .queryParam("fromTimeStamp", "{fromTimeStamp}")
                .queryParam("toTimeStamp", "{toTimeStamp}")
                .encode().toUriString();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        headers.setETag(etag);
        headers.setIfNoneMatch(etag);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CubeData[]> responseEntity = null;
        
        try {
        	var request = new HttpEntity(headers);
        	cubeData = getBody(restTemplate, uri, request, params);
        } catch (HttpClientErrorException e) {
            accessToken = authorizationToken.getAccessToken(true);
            headers.setBearerAuth(accessToken);
            
            var request = new HttpEntity(headers);

            cubeData = getBody(restTemplate, uri, request, params);
        }

        return cubeData;
    }

	public void downloadCsv(ICsvBeanWriter csvBeanWriter, CubeData[] cubeDataList) throws IOException {
		String[] header = {"ID", "External Temperature", "Battery Current", "Battery Voltage", "Battery Temperature",
				"Magnetic Field X", "Magnetic Field Y", "Magnetic Field Z",
				"Euler Angle X", "Euler Angle Y", "Euler Angle Z",
				"Linear Speed X", "Linear Speed Y", "Linear Speed Z",
				"Angular Speed X", "Angular Speed Y", "Angular Speed Z",
				"Transmitted Transciever Power", "Received Transceiver Power",
				"Time Stamp Cube", "Time Stamp Base"};

		String[] fieldMapping = {"id", "externalTemperature", "batteryCurrent", "batteryVoltage", "batteryTemperature",
						"magneticFieldX", "magneticFieldY", "magneticFieldY",
						"eulerAngleX", "eulerAngleY", "eulerAngleZ",
						"linearSpeedX", "linearSpeedY", "linearSpeedZ",
						"linearSpeedZ", "linearSpeedZ", "linearSpeedZ",
						"transmittedTransceiverPower", "transmittedTransceiverPower",
						"timeStampCube", "timeStampBase"};
		
		csvBeanWriter.writeHeader(header);
		
		for (CubeData cubeData : cubeDataList) {
			csvBeanWriter.write(cubeData, fieldMapping);
		}

	}
	
	private CubeData[] getBody (RestTemplate restTemplate, String uri, HttpEntity request, 
			Map<String, String> params) {
		ResponseEntity<CubeData[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                request, CubeData[].class, params);
		
		CubeData[] cubeData;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	cubeData = cubeServiceCacheUtil.cacheResult(true, responseEntity);
		} else {
			cubeData = cubeServiceCacheUtil.cacheResult(false, responseEntity);
		}
        etag = responseEntity.getHeaders().getETag();
        
        return cubeData;
	}
}
