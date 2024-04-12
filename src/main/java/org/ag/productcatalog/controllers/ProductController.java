package org.ag.productcatalog.controllers;

import org.ag.productcatalog.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return null;
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") long productId) {
        ProductDto product = new ProductDto();
        product.setId(productId);
        product.setName("iphone");
        product.setDescription("iphone");
        product.setPrice(12.0);
        return product;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productDto;
    }


}
