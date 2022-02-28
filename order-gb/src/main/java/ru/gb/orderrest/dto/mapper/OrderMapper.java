package ru.gb.orderrest.dto.mapper;


import org.mapstruct.Mapper;
import ru.gb.orderrest.dto.OrderDto;
import ru.gb.orderrest.entity.Order;

@Mapper(uses = {ProductMapper.class,ProductListMapper.class})
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);

}
