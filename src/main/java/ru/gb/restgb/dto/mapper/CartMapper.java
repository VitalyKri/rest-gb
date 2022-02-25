package ru.gb.restgb.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.gb.restgb.dao.ManufacturerDao;
import ru.gb.restgb.dto.CartDto;
import ru.gb.restgb.entity.Cart;
import ru.gb.restgb.entity.Manufacturer;
import ru.gb.restgb.entity.Product;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface CartMapper {

    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);

    default String getManufacturer(Manufacturer manufacturer){
        return manufacturer.getName();
    }

    // @Context spring из контекста сам найдет это поле
    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao){
        return manufacturerDao.findByName(manufacturer).orElseThrow(NoSuchElementException::new);
    }


}
