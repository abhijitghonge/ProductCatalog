package org.ag.productcatalog.stubs;

import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceStub implements IProductService {

    Map<Long, Product> products;
    public ProductServiceStub() {
        products = new HashMap<>();
    }
    @Override
    public List<Product> getProducts() {
        return (List<Product>) products.values();
    }

    @Override
    public Product getProduct(long productId) {
        return products.get(productId);
    }

    @Override
    public Product createProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product updateProduct(long id, Product productDto) {
        products.put(id, productDto);
        return productDto;
    }

    @Override
    public void deleteProduct(long productId) {
        products.remove(productId);
    }

    @Override
    public Product getProductDetails(Long pid, Long uid) {
        return null;
    }
}
