package ru.gb.onlineshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Product;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findById(long id);

    Product findByTitle(String name);

    List<Product> findAll();

    List<Product> findAllByTitleLike(String title);
}
