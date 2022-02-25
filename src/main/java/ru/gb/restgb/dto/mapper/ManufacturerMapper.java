package ru.gb.restgb.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.restgb.dto.ManufacturerDto;

import ru.gb.restgb.entity.Manufacturer;



@Mapper
public interface ManufacturerMapper {

    @Mapping(source = "manufacturerId",target = "id")
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);
    @Mapping(source = "id",target = "manufacturerId")
    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);

}
