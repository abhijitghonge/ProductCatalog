package org.ag.productcatalog.services;

import org.ag.productcatalog.clients.userservice.UserApiClient;
import org.ag.productcatalog.dtos.UserDto;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class StorageProductService implements IProductService{

    private ProductRepo productRepo;

    private UserApiClient userApiClient;

    public StorageProductService(ProductRepo productRepo, UserApiClient userApiClient) {
        this.productRepo = productRepo;
        this.userApiClient = userApiClient;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(long productId) {
        return productRepo.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return productRepo.save(product);
    }





    @Override
    public void deleteProduct(long productId) {

    }

    @Override
    public Product getProductDetails(Long pid, Long uid) {

        UserDto user = userApiClient.getUser(uid);
        if(user !=null) {
            System.out.println("USER EMAIL = "+user.getEmail());
            return productRepo.findById(pid).get();
        }
        return null;

    }
}
