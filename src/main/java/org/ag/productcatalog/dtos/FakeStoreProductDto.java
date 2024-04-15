package org.ag.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private long id;
    private String title;

    private String description;

    private String  category;
    private double price;
    private String image;

    private FakeStoreRatingDto ratingDto;
}
