package ru.gb.orderrest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.orderrest.dto.CartDto;


import java.util.List;


@FeignClient(url = "localhost:8080/api/v1/cart", value = "manufacturerGateway")
public interface CartGateway {

    @GetMapping(produces = "application/json;charset=UTF-8")
    List<CartDto> getListCart();

    @GetMapping(value = "{id}",produces = "application/json;charset=UTF-8")
    CartDto getCart(@PathVariable("id")Long id);
}
