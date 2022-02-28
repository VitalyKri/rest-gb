package ru.gb.restgb.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.restgb.dto.CartDto;
import ru.gb.restgb.dto.ProductDto;
import ru.gb.restgb.dto.mapper.ProductMapper;
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
    public List<CartDto> findAll() {

        return cartService.findAll();

    }

    @GetMapping("/{id}")
    public CartDto findById(@PathVariable("id") Long id) {

        return cartService.findById(id);

    }


    @GetMapping("/addProduct/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable("productId") Long id) {

        CartDto cart = cartService.findById(1L);
        ProductDto productDto = productService.findById(id);
        if (productDto == null){
            return new ResponseEntity<>("Не найден продукт",HttpStatus.NOT_FOUND);
        }
        if(cart.addProduct(productDto)){
            cartService.save(cart);
            return new ResponseEntity<>("Продукт добавлен",HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось добавить продукт",HttpStatus.NOT_FOUND);

    }



    @GetMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long id) {

        CartDto cartDto = cartService.findById(1L);

        ProductDto productDto = productService.findById(id);
        if (productDto == null){
            return new ResponseEntity<>("Не найден продукт",HttpStatus.NOT_FOUND);
        }
        if(cartDto.deleteProduct(productDto)){
            cartService.save(cartDto);
            return new ResponseEntity<>("Продукт удален",HttpStatus.OK);
        }
        return new ResponseEntity<>("Не получилось удалить продукт",HttpStatus.NOT_FOUND);
    }

}
