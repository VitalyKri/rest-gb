package ru.gb.restgb.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ManufacturerDto {
    @JsonProperty(value = "id")
    private Long manufacturerId;
    @NotBlank
    private String name;

}
