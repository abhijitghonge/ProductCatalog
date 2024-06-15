package org.ag.productcatalog.services;

import org.ag.productcatalog.clients.fakestore.FakeStoreApiClient;
import org.ag.productcatalog.dtos.FakeStoreProductDto;
import org.ag.productcatalog.models.Category;
import org.ag.productcatalog.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ProductService implements IProductService {

    private final FakeStoreApiClient fakeStoreApiClient;

    private final RedisTemplate<String, Object> redisTemplate;


    public ProductService( FakeStoreApiClient fakeStoreApiClient, RedisTemplate<String, Object> redisTemplate ) {

        this.fakeStoreApiClient = fakeStoreApiClient;

        this.redisTemplate = redisTemplate;
    }



    @Override
    public List<Product> getProducts() {

        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreApiClient.getProducts();

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProducts) {
            Product product = getProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProduct(long productId) {
        if (productId < 1) {
            throw new IllegalArgumentException("Invalid product id: " + productId);
        }

        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get("PRODUCT", productId);

        if(!Objects.isNull(fakeStoreProductDto)){
            return getProduct(fakeStoreProductDto);
        }
        fakeStoreProductDto = fakeStoreApiClient.getProduct(productId);
        redisTemplate.opsForHash().put("PRODUCT", productId, fakeStoreProductDto);

        return getProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {


        return getProduct(fakeStoreApiClient.createProduct(getFakeStoreProductDto(product)));
    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.updateProduct(id, getFakeStoreProductDto(product));
        return getProduct(fakeStoreProductDto);

    }

    public void deleteProduct(long id){
        fakeStoreApiClient.deleteProduct(id);
    }

    @Override
    public Product getProductDetails(Long pid, Long uid) {
        return null;
    }

    private Product getProduct(FakeStoreProductDto fakeStoreProductDto) {
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

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        return FakeStoreProductDto.builder()
                .title(product.getName())
                .image(product.getImageUrl())
                .price(product.getPrice())
                .description(product.getDescription()).build();
    }




}

