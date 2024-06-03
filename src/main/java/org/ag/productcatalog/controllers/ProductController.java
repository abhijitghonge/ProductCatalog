package org.ag.productcatalog.controllers;

import org.ag.productcatalog.dtos.CategoryDto;
import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.models.Category;
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

    private final IProductService productService;

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


    @GetMapping("{pid}/{uid}")
    public ProductDto getProductDetailsBasedOnUserScope(@PathVariable Long pid,@PathVariable Long uid) {


        Product product = productService.getProductDetails(pid,uid);
        return getProductDto(product);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(getProduct(productDto));
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, getProduct(productDto));
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") long productId) {

        productService.deleteProduct(productId);

    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        Category category = new Category();
        if(productDto.getCategoryDto() != null) {
            category.setName(productDto.getCategoryDto().getName());
            category.setDescription(productDto.getCategoryDto().getDescription());
        }

        product.setCategory(category);
        return product;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryDto(getCategoryDto(product.getCategory()));
        return productDto;
    }

    private CategoryDto getCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }


}
