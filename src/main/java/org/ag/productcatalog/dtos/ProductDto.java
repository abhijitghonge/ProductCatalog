package org.ag.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ag.productcatalog.models.Category;

@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
