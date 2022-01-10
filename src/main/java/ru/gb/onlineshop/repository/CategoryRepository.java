package ru.gb.onlineshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
