package ru.gb.restgb.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.restgb.entity.enums.Status;
import ru.gb.restgb.entity.Cart;
import java.util.List;

public interface CartDao extends JpaRepository<Cart,Long> {

    List<Cart> findAllByStatus(Status status);
    List<Cart> findAllByStatus(Status status, Pageable pageable);
    List<Cart> findAllByStatus(Status status, Sort sort);

}
