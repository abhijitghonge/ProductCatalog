package org.ag.productcatalog.clients.userservice;

import org.ag.productcatalog.dtos.FakeStoreProductDto;
import org.ag.productcatalog.dtos.UserDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserApiClient {
    RestTemplate restTemplate;

    public UserApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public UserDto getUser(Long userId){
        return restTemplate.getForEntity("http://userservice/users/{id}", UserDto.class, userId)
                .getBody();
    }

}
