package ru.gb.orderrest.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.orderrest.dto.ProductDto;
import ru.gb.orderrest.entity.Product;


import java.util.NoSuchElementException;

@Mapper
public interface ProductMapper {
    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);

}
