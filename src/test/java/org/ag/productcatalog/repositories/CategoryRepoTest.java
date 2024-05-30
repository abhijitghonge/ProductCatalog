package org.ag.productcatalog.repositories;

import jakarta.transaction.Transactional;
import org.ag.productcatalog.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    void testQueries() {
        //List<Product> products = productRepo.findProductByPriceBetween(1000D,100012D);
        String productName = productRepo.getProductNameById(52l);
        System.out.println(productName);

        String categoryName = productRepo.findCategoryNameByProductId(52l);

        System.out.println("Category name:"+categoryName);

    }

}
