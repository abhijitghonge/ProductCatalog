package org.ag.productcatalog.services;

import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(long productId);

    ProductDto createProduct(ProductDto productDto);
}
