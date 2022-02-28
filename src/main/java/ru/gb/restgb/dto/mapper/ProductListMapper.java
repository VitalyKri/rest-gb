package ru.gb.restgb.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.gb.restgb.dao.ManufacturerDao;
import ru.gb.restgb.dto.ProductDto;
import ru.gb.restgb.entity.Manufacturer;
import ru.gb.restgb.entity.Product;

import java.util.List;

@Mapper(uses ={ ManufacturerMapper.class,ProductMapper.class})
public interface ProductListMapper {
    List<Product> productToProductDto(List<ProductDto> product, @Context ManufacturerDao manufacturerDao);
    List<ProductDto> productDtoToProduct(List<Product> product);


}
