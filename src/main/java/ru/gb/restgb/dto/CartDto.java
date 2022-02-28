package ru.gb.restgb.dto;


import lombok.*;
import ru.gb.restgb.entity.Product;
import ru.gb.restgb.entity.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    @NotBlank
    private String status;

    private Set<ProductDto> products = new HashSet<>();

    public boolean addProduct(ProductDto product){
        return products.add(product);
    }

    public boolean deleteProduct(ProductDto product){
        return products.remove(product);
    }
}
