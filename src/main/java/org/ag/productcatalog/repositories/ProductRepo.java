package org.ag.productcatalog.repositories;

import org.ag.productcatalog.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("select p.name from Product p where p.id=?1")
    String getProductNameById(long id);

    @Query("select c.name from Category c join Product p on c.id = p.category.id and p.id=?1")
    String findCategoryNameByProductId(long id);

    Page<Product> findByNameEquals(String query, Pageable pageable);
}
