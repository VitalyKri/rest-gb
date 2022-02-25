package ru.gb.restgb.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@JsonTest
class ManufacturerDtoTest {
    @Autowired
    private  ObjectMapper objectMapper;

    private ManufacturerDto manufacturerDto;

    @BeforeEach
    void setUp() {
        manufacturerDto = ManufacturerDto.builder()
                .manufacturerId(1L)
                .name("Test Company")
                .build();
    }

    @Test
    void testSerializeDto() throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(manufacturerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeDto() throws JsonProcessingException {
        String json = "{\"name\":\"Test Company\",\"id\":1}";
        ManufacturerDto testManufacturerDto = objectMapper.readValue(json, ManufacturerDto.class);
        System.out.println(testManufacturerDto);
    }
}