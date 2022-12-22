package com.abnamro.retailer.mapper;

import com.abnamro.retailer.entity.Product;
import com.abnamro.retailer.entity.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public Product mapDtoToProduct(ProductDTO productDTO);
}
