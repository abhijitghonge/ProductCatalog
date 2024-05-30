package org.ag.productcatalog.services;

import org.ag.productcatalog.models.Product;
import org.ag.productcatalog.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> searchProduct(String query, int pageNum, int pageSize) {
        Sort sort = Sort.by("price").descending().and(Sort.by("id").ascending());
        return productRepo.findByNameEquals(query, PageRequest.of(pageNum, pageSize, sort));
    }
}
