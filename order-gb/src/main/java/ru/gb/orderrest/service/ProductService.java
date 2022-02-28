package ru.gb.orderrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.orderrest.dao.ProductDao;
import ru.gb.orderrest.dto.ProductDto;
import ru.gb.orderrest.dto.mapper.ProductMapper;
import ru.gb.orderrest.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;



    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return  productMapper.toProductDto(productDao.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productDao.
                findAll()
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

}
