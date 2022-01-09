package ru.gb.onlineshop.service;

import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.Category;
import ru.gb.onlineshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}
