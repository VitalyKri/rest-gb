package ru.gb.restgb.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ProductDto {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotNull
    @DecimalMin(value = "0.0",inclusive = false)
    @Digits(integer = 5,fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @NotNull
    private Status status;

    private String manufacturer;
}
