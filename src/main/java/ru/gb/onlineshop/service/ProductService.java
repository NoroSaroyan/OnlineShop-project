package ru.gb.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.Product;
import ru.gb.onlineshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void edit(long id, Product newProduct) {
        Optional<Product> found = productRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            found.get().setTitle(newProduct.getTitle());
            found.get().setDescription(newProduct.getDescription());
            found.get().setPrice(newProduct.getPrice());
            save(newProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void delete(long id) {
        productRepository.delete(findById(id));
    }

    public List<Product> findAllByOrderByIdAsc() {
        return null;
    }

    public Product findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByCategoryId(long categoryId) {
        //TODO can't execute the query with categoryId.
        return productRepository.findAll();
    }

    public long count() {
        return productRepository.count();
    }

    public List<Product> findAll() {
        return (List<Product>)productRepository.findAll();
    }
}
