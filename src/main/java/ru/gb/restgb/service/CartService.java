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
import ru.gb.restgb.entity.Cart;
import ru.gb.restgb.entity.enums.Status;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;

    public Cart save(Cart cart) {
        if (cart.getId() != null) {
            Optional<Cart> productFromDbOptional = cartDao.findById(cart.getId());
            if (productFromDbOptional.isPresent()) {
                Cart cartFromDb = productFromDbOptional.get();
                cartFromDb.setStatus(cart.getStatus());
                return cartDao.save(cartFromDb);
            }
        }
        return cartDao.save(cart);
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartDao.findAll();
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

    public List<Cart> findAllActive() {
        return cartDao.findAllByStatus(Status.ACTIVE);
    }


    public List<Cart> findAllActive(int page, int size) {
        return cartDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    public List<Cart> findAllActiveSortedById(Sort.Direction direction) {
        return cartDao.findAllByStatus(Status.ACTIVE, Sort.by(direction, "id"));
    }

    public List<Cart> findAllActiveSortedById(int page, int size, Sort.Direction direction) {
        return cartDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(direction, "id")));
    }

    @Transactional(propagation = Propagation.NEVER)
    public long count() {
        System.out.println(cartDao.count());
        // какая-то логика
        return cartDao.count();
    }

    
}
