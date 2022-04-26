package com.reservoir.datareservoir.client.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.reservoir.datareservoir.client.config.properties.AuthorizationProperties;
import com.reservoir.datareservoir.client.domain.model.TokenModel;
import com.reservoir.datareservoir.client.domain.service.properties.UrlProperties;

@Component
public class AuthorizationToken {

	@Autowired
	private AuthorizationProperties authorizationProperties;
	
	@Autowired
    private UrlProperties urlProperties;

    private String accessToken;
    private TokenModel tokenModel;
    
    public String getAccessToken(boolean expired) {
        if (accessToken == null || expired) {
            accessToken = getNewToken();
        }
        return accessToken;
    }
    
    private String getNewToken() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", authorizationProperties.getAuthUsername());
        map.add("password", authorizationProperties.getAuthPassword());

        return getToken(map);
    }

    private String getToken(MultiValueMap<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorizationProperties.getAuthClientUsername(), authorizationProperties.getAuthClientPassword());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<TokenModel> response = new RestTemplate().exchange(urlProperties.getAuthorizationUrl(), HttpMethod.POST, request,
                TokenModel.class);
        tokenModel = response.getBody();
        return tokenModel.getAccess_token();
    }
}
