package com.abnamro.retailer.entity.dto;

import com.abnamro.retailer.util.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderProductDTO {

    @NotNull(message = ErrorConstants.ERROR_PRODUCT_ID_NOT_NULL)
    @Min(value = 1, message = ErrorConstants.ERROR_PRODUCT_ID_POSITIVE)
    private Long productId;

    @NotNull(message = ErrorConstants.ERROR_QUANTITY_NOT_NULL)
    @Min(value = 1, message = ErrorConstants.ERROR_QUANTITY_POSITIVE)
    private Integer quantity;

}
