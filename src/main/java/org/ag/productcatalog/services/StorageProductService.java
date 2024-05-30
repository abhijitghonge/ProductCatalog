package org.ag.productcatalog.services;

import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.repositories.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class StorageProductService implements IProductService{

    private ProductRepo productRepo;

    public StorageProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
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
}
