package ru.gb.orderrest.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.orderrest.entity.Product;


import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
    List<Product> findAllByTitleContaining(String title);

}
