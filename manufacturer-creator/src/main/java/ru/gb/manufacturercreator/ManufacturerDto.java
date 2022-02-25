package ru.gb.manufacturercreator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

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
