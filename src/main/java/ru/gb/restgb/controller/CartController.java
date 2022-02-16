package ru.gb.restgb.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.restgb.entity.Cart;
import ru.gb.restgb.entity.Product;
import ru.gb.restgb.service.CartService;
import ru.gb.restgb.service.ProductService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;


    @GetMapping
    public List<Cart> findAll() {

        return cartService.findAll();

    }


    @GetMapping("/addProduct/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable("productId") Long id) {

        Cart cart = cartService.findById(1L);

        Product product = productService.findById(id);
        if (product == null){
            return new ResponseEntity<>("Не найден продукт",HttpStatus.NOT_FOUND);
        }
        if(cart.addProduct(product)){
            cartService.save(cart);
            return new ResponseEntity<>("Продукт добавлен",HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось добавить продукт",HttpStatus.NOT_FOUND);

    }



    @GetMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id) {

        Cart cart = cartService.findById(1L);

        Product product = productService.findById(id);
        if (product == null){
            return new ResponseEntity<>("Не найден продукт",HttpStatus.NOT_FOUND);
        }
        if(cart.deleteProduct(product)){
            cartService.save(cart);
            return new ResponseEntity<>("Продукт удален",HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось удалить продукт",HttpStatus.NOT_FOUND);
    }

}
