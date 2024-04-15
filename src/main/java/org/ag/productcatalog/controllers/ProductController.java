package org.ag.productcatalog.controllers;

import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        try{
            if(productId < 1){
                throw new IllegalArgumentException("Invalid product id");
            }
            headers.add("called by", "smart frontend");

            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
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
