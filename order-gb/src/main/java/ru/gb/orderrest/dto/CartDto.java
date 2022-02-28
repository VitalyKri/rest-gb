package ru.gb.orderrest.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
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

    private final Set<ProductDto> products = new HashSet<>();

    public boolean addProduct(ProductDto product){
        return products.add(product);
    }

    public boolean deleteProduct(ProductDto product){
        return products.remove(product);
    }
}
