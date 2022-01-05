package ru.gb.onlineshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findById(Long id);

    List<Order> findAllByUserId(String userId);

    Optional<Order> findByPriceAndUserId(double price,String userId);
}
