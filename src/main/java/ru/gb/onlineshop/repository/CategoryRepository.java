package ru.gb.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
