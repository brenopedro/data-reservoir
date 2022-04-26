package com.reservoir.datareservoir.client.domain.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.supercsv.io.ICsvBeanWriter;

import com.reservoir.datareservoir.client.config.auth.AuthorizationToken;
import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.model.RocketData;
import com.reservoir.datareservoir.client.domain.service.properties.UrlProperties;

@Component
public class RocketDataServiceClient {

	@Autowired
	private AuthorizationToken authorizationToken;
	
	@Autowired
	private UrlProperties urlProperties;
	
    public RocketData[] getRocketData(PropertiesFilter propertiesFilter) {
        String accessToken = authorizationToken.getAccessToken(false);
        RocketData[] rocketData;
        
        Map<String, String> params = new HashMap<>();
        params.put("fromTimeStamp", propertiesFilter.getFromTimeStamp());
        params.put("toTimeStamp", propertiesFilter.getToTimeStamp());

        String uri = UriComponentsBuilder.fromHttpUrl(urlProperties.getRocketData())
                .queryParam("fromTimeStamp", "{fromTimeStamp}")
                .queryParam("toTimeStamp", "{toTimeStamp}")
                .encode().toUriString();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            var request = new HttpEntity(headers);

            rocketData = new RestTemplate().exchange(uri, HttpMethod.GET, 
            		request, RocketData[].class, params).getBody();
        } catch (HttpClientErrorException e) {
            accessToken = authorizationToken.getAccessToken(true);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            var request = new HttpEntity(headers);

            rocketData = new RestTemplate().exchange(uri, HttpMethod.GET, 
            		request, RocketData[].class, params).getBody();
        }

        return rocketData;
    }

	public void downloadCsv(ICsvBeanWriter csvBeanWriter, RocketData[] rocketDataList) throws IOException {
		String[] header = {"ID", "Altitude", "External Temperature", "Acceleration", 
				"Euler Angle X", "Euler Angle Y", "Euler Angle Z", 
				"Position Gps X", "Position Gps Y", "Position Gps Z", 
				"Magnetic Field X", "Magnetic Field Y", "Magnetic Field Z", 
				"Linear Speed X", "Linear Speed Y", "Linear Speed Z", 
				"Angular Speed X", "Angular Speed Y", "Angular Speed Z", 
				"Time Stamp Rocket", "Time Stamp Base"};
		
		String[] fieldMapping = {"id", "altitude", "externalTemperature", "acceleration", 
				"eulerAngleX", "eulerAngleY", "eulerAngleZ", 
				"positionGpsX", "positionGpsY", "positionGpsZ", 
				"magneticFieldX", "magneticFieldY", "magneticFieldZ", 
				"linearSpeedX", "linearSpeedY", "linearSpeedZ", 
				"angularSpeedX", "angularSpeedY", "angularSpeedZ", 
				"timeStampRocket", "timeStampBase"};
		
		csvBeanWriter.writeHeader(header);
		
		for (RocketData rocketData: rocketDataList) {
			csvBeanWriter.write(rocketData, fieldMapping);
		}
		
	}
}
