package ru.gb.orderrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.orderrest.dto.CartDto;
import ru.gb.orderrest.dto.OrderDto;
import ru.gb.orderrest.dto.ProductDto;
import ru.gb.orderrest.service.CartGateway;
import ru.gb.orderrest.service.OrderService;
import ru.gb.orderrest.service.ProductService;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CartGateway cartGateway;

    @GetMapping
    public List<OrderDto> findAll() {

        return orderService.findAll();

    }

    @GetMapping("/{productId}")
    public OrderDto findById(@PathVariable("productId") Long id) {

        return orderService.findById(id);

    }

    @GetMapping("/copyCart")
    public ResponseEntity<?> copyCart(@RequestParam("cartId") Long id) {
        CartDto cart = cartGateway.getCart(id);

        OrderDto orderDto = OrderDto.builder()
                .client("user")
                //.createdDate(LocalDateTime.now())
                .products(cart.getProducts())
                .build();

        orderService.save(orderDto);
        return new ResponseEntity<>("Заказ скопирован", HttpStatus.OK);

    }

    @GetMapping("/create")
    public ResponseEntity<?> createOrder() {

        OrderDto orderDto = OrderDto.builder()
                .client("user")
                .createdDate(LocalDateTime.now())
                .build();

        orderService.save(orderDto);
        return new ResponseEntity<>("Заказ c № "+orderDto.getId(), HttpStatus.OK);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("productId") Long id) {
        CartDto cart = cartGateway.getCart(id);

        OrderDto orderDto = OrderDto.builder()
                .client("user")
                .createdDate(LocalDateTime.now())
                .build();

        orderService.save(orderDto);
        return new ResponseEntity<>("Заказ удален", HttpStatus.OK);

    }

    @GetMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestParam("orderId") Long orderId, @RequestParam("productId") Long productId) {

        OrderDto order = orderService.findById(orderId);
        if (order == null) {
            return new ResponseEntity<>("Не найден заказ", HttpStatus.NOT_FOUND);
        }
        ProductDto productDto = productService.findById(productId);
        if (productDto == null) {
            return new ResponseEntity<>("Не найден продукт", HttpStatus.NOT_FOUND);
        }
        if (order.addProduct(productDto)) {
            orderService.save(order);
            return new ResponseEntity<>("Продукт добавлен", HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось добавить продукт", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestParam("orderId") Long orderId, @RequestParam("productId") Long productId) {

        OrderDto order = orderService.findById(orderId);
        if (order == null) {
            return new ResponseEntity<>("Не найден заказ", HttpStatus.NOT_FOUND);
        }
        ProductDto productDto = productService.findById(productId);
        if (productDto == null) {
            return new ResponseEntity<>("Не найден продукт", HttpStatus.NOT_FOUND);
        }
        if (order.deleteProduct(productDto)) {
            orderService.save(order);
            return new ResponseEntity<>("Продукт добавлен", HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось добавить продукт", HttpStatus.NOT_FOUND);
    }

}
