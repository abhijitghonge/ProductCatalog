package org.ag.productcatalog.clients.fakestore;

import org.ag.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class FakeStoreApiClient {
    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProduct(long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId)
                .getBody();

        return fakeStoreProductDto;
    }
}
