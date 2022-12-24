package com.abnamro.retailer.util;

import com.abnamro.retailer.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;

public class TestUtils {


    public static Product buildRandomProductWithoutId() {
        return Product.builder()
                .name(RandomStringUtils.randomAlphabetic(200))
                .description(RandomStringUtils.randomAlphabetic(1000))
                .price(BigDecimal.valueOf(RandomUtils.nextDouble()))
                .build();
    }

    public static Product buildRandomProduct() {
        return Product.builder()
                .id(RandomUtils.nextLong())
                .name(RandomStringUtils.randomAlphabetic(200))
                .description(RandomStringUtils.randomAlphabetic(1000))
                .price(BigDecimal.valueOf(RandomUtils.nextDouble()))
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
