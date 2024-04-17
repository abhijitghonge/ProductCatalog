package org.ag.productcatalog.services;

import org.ag.productcatalog.clients.fakestore.FakeStoreApiClient;
import org.ag.productcatalog.dtos.FakeStoreProductDto;
import org.ag.productcatalog.models.Category;
import org.ag.productcatalog.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreApiClient fakeStoreApiClient;



    public ProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient) {
        this.restTemplateBuilder = restTemplateBuilder;

        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<FakeStoreProductDto> fakeStoreProductDtos = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>(){}).getBody();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            Product product = getProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProduct(long productId){
        if(productId<1){
            throw new IllegalArgumentException("Invalid product id: " + productId);
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);

        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForEntity("https://fakestoreapi.com/products",getFakeStoreProductDto(product), FakeStoreProductDto.class )
                .getBody();
        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = putForEntity("https://fakestoreapi.com/products/{id}",getFakeStoreProductDto(product), FakeStoreProductDto.class, id )
                .getBody();
        return getProduct(fakeStoreProductDto);
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
}

