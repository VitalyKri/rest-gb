package ru.gb.restgb.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.restgb.dto.CartDto;
import ru.gb.restgb.entity.Cart;
import ru.gb.restgb.entity.Manufacturer;


@Mapper(uses ={ ManufacturerMapper.class,ProductMapper.class,ProductListMapper.class})
public interface CartMapper {

    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);

    Manufacturer map(String value);

}


