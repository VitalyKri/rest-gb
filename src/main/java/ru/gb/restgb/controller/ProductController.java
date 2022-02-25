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
import ru.gb.restgb.dto.ProductDto;
import ru.gb.restgb.dto.ProductManufacturerDto;
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
    public List<ProductDto> getProductList() {
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<? extends ProductDto> getProduct(@PathVariable("productId") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ProductDto productDto = productService.findById(id);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //    @PostMapping("/{productId}") для валидации нужна аннотация @Validated которая находится в дто
    @PostMapping
    public ResponseEntity<?> handlerCreate(@Validated @RequestBody ProductDto productDto) {
        ProductDto save = productService.save(productDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/product/" + save.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> handlerUpdate(@PathVariable("productId") Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        productService.save(productDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//
    }

//    @PostMapping("/{productId}") для валидации нужна аннотация @Validated которая находится в дто
//    public ResponseEntity<?> handlerUpdate(@Validated @PathVariable("productId") Long id, @RequestBody Product product){
//        product.setId(id);
//        productService.save(product);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//
//    }

    @GetMapping("/info")
    public List<ProductManufacturerDto> getInfoProductList(){
        return productService.findAllInfo();
    }

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
