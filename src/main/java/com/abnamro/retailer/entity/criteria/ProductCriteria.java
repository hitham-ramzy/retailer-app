package com.abnamro.retailer.entity.criteria;

import lombok.Data;


@Data
public class ProductCriteria {

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private BigDecimalFilter price;
}
