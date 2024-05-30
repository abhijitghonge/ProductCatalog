package org.ag.productcatalog.services;

import org.ag.productcatalog.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(long productId);

    Product createProduct(Product product);

    Product updateProduct(long id, Product product);

    void deleteProduct(long productId);
}
