package ru.gb.orderrest.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.orderrest.dao.OrderDao;
import ru.gb.orderrest.dto.OrderDto;
import ru.gb.orderrest.dto.mapper.OrderMapper;
import ru.gb.orderrest.entity.Order;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        Order save = orderDao.save(order);
        return orderMapper.toOrderDto(save);
    }

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        return orderMapper.toOrderDto(
                orderDao.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        return orderDao.findAll().stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            orderDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }
    }




    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(orderDao.count());
        // какая-то логика
        return orderDao.count();
    }
}
