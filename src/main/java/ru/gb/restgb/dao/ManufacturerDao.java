package ru.gb.restgb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.restgb.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer,Long> {
    Optional<Manufacturer> findByName(String name);
}
