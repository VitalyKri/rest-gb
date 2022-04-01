package ru.gb.orderrest.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.orderrest.dto.ProductDto;
import ru.gb.orderrest.entity.Product;


import java.util.List;

@Mapper(uses ={ ProductMapper.class})
public interface ProductListMapper {
    List<Product> productToProductDto(List<ProductDto> product);
    List<ProductDto> productDtoToProduct(List<Product> product);


}
