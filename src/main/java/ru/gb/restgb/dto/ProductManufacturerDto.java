package ru.gb.restgb.dto;


import lombok.*;
import ru.gb.restgb.entity.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductManufacturerDto {
    private Long id;
    @NotBlank
    private String title;

    @NotNull
    @DecimalMin(value = "0.0",inclusive = false)
    @Digits(integer = 5,fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate date;

    private ManufacturerDto manufacturerDto;
}
