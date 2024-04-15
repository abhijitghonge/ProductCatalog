package org.ag.productcatalog.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;

}
