package org.ag.productcatalog.controllers;


import org.ag.productcatalog.dtos.CategoryDto;
import org.ag.productcatalog.dtos.ProductDto;
import org.ag.productcatalog.dtos.SearchRequestDto;
import org.ag.productcatalog.models.Category;
import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        List<ProductDto> searchResults = new ArrayList<>();

        Page<Product> products = searchService.searchProduct(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize());
       /* for (Product product : products) {
            searchResults.add(getProductDto(product));
        }*/
        return products;
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
