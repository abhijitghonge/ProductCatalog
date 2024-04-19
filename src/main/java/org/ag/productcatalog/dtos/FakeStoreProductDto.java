package org.ag.productcatalog.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FakeStoreProductDto {

    private long id;
    private String title;

    private String description;

    private String  category;
    private double price;
    private String image;

    private FakeStoreRatingDto ratingDto;
}
