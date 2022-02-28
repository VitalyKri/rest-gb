package ru.gb.restgb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.restgb.dao.CartDao;
import ru.gb.restgb.dto.CartDto;
import ru.gb.restgb.dto.mapper.CartMapper;
import ru.gb.restgb.entity.Cart;
import ru.gb.restgb.entity.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;
    private final CartMapper cartMapper;

    public CartDto save(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        if (cart.getId() != null) {
            Optional<Cart> productFromDbOptional = cartDao.findById(cart.getId());
            if (productFromDbOptional.isPresent()) {
                Cart cartFromDb = productFromDbOptional.get();
                cartFromDb.setStatus(cart.getStatus());
            }
        }
        return cartMapper.toCartDto(cartDao.save(cart));
    }



    @Transactional(readOnly = true)
    public CartDto findById(Long id) {
        return cartMapper.toCartDto(
                cartDao.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<CartDto> findAll() {
        return cartDao.findAll().stream()
                .map(cartMapper::toCartDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }

    }





    public void disableById(Long id) {
        cartDao.findById(id).ifPresent(
                p -> {
                    p.setStatus(Status.DISABLE);
                    cartDao.save(p);
                }
        );
    }

    public List<CartDto> findAllActive() {
        return cartDao.findAllByStatus(Status.ACTIVE).stream()
                .map(cartMapper::toCartDto)
                .collect(Collectors.toList());
    }


    public List<CartDto> findAllActive(int page, int size) {
        return cartDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size))
                .stream()
                .map(cartMapper::toCartDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveSortedById(Sort.Direction direction) {
        return cartDao.findAllByStatus(Status.ACTIVE, Sort.by(direction, "id"))
                .stream()
                .map(cartMapper::toCartDto)
                .collect(Collectors.toList());
    }

    public List<CartDto> findAllActiveSortedById(int page, int size, Sort.Direction direction) {
        return cartDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(direction, "id")))
                .stream()
                .map(cartMapper::toCartDto)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(cartDao.count());
        // какая-то логика
        return cartDao.count();
    }


}
