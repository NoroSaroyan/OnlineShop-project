package ru.gb.onlineshop.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Cart;
import java.util.Optional;

//@ComponentScan("ru.gb.entity.Cart")
//@Repository("cartRepository")
public interface CartRepository{
    Optional<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserEmail(String email);
}