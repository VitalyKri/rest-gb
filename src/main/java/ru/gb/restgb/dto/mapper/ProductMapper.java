package ru.gb.restgb.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.gb.restgb.dao.ManufacturerDao;
import ru.gb.restgb.dto.ProductDto;
import ru.gb.restgb.dto.ProductManufacturerDto;
import ru.gb.restgb.entity.Manufacturer;
import ru.gb.restgb.entity.Product;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao);
    ProductDto toProductDto(Product product);

    @Mapping(source = "manufacturer",target = "manufacturerDto")
    ProductManufacturerDto toProductManufacturerDto(Product product);

    default String getManufacturer(Manufacturer manufacturer){
        return manufacturer.getName();
    }

    // @Context spring из контекста сам найдет это поле
    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao){
        return manufacturerDao.findByName(manufacturer).orElseThrow(NoSuchElementException::new);
    }
}
