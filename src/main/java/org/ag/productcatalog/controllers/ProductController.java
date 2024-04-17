package org.ag.productcatalog.controllers;

import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long productId) {
        Product product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(getProduct(productDto));
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, getProduct(productDto));
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable("id") long productId) {
        ProductDto product = new ProductDto();
        product.setId(productId);
        product.setName("iphone");
        product.setDescription("iphone");
        product.setPrice(12.0);


    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }


}
