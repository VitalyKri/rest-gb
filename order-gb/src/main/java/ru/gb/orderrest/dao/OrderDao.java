package ru.gb.orderrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.orderrest.entity.Order;

public interface OrderDao extends JpaRepository<Order,Long> {

}
