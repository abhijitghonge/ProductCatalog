package org.ag.productcatalog.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Builder
@ToString
public class FakeStoreProductDto implements Serializable {

    private long id;
    private String title;

    private String description;

    private String  category;
    private double price;
    private String image;

    private FakeStoreRatingDto ratingDto;
}
