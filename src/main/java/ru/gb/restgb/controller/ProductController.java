package ru.gb.restgb.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.gb.restgb.entity.Product;
import ru.gb.restgb.service.ProductService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // не создаются view
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getProductList() {
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<? extends Product> getProduct(@PathVariable("productId") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> handlerCreate(@RequestBody Product product) {
        Product save = productService.save(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/product/" + save.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> handlerUpdate(@PathVariable("productId") Long id, @RequestBody Product product) {
        product.setId(id);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//
    }

//    @PostMapping("/{productId}") для валидации нужна аннотация @Validated
//    public ResponseEntity<?> handlerUpdate(@Validated @PathVariable("productId") Long id, @RequestBody Product product){
//        product.setId(id);
//        productService.save(product);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//
//    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable("productId") Long id) {
        productService.deleteById(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {

        List<String> allErrors = new ArrayList<>();

        e.getBindingResult().getAllErrors().stream()
            .map(FieldError.class::cast)
            .forEach(a->
                allErrors.add(String.format("Bad request $s : $s : Rejected value : $s",
                        a.getField(),
                        a.getDefaultMessage(),
                        a.getRejectedValue())));

        return new ResponseEntity<>(allErrors, HttpStatus.NOT_FOUND);}
}
