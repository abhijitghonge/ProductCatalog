package org.ag.productcatalog.controllers;

import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProductDto> getProducts() {
        return null;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") long productId) {
       return productService.getProduct(productId);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productDto;
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productDto;
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable("id") long productId){
        ProductDto product = new ProductDto();
        product.setId(productId);
        product.setName("iphone");
        product.setDescription("iphone");
        product.setPrice(12.0);


    }


}
