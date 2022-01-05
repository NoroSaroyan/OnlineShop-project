package ru.gb.onlineshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
//
//    Optional<Product> findById(Long id);
//
//    void update(Long id);
//
//    List<Product> findAll();
//
//    void deleteById(Long id);
//
//    @Query("SELECT p FROM Product  as p WHERE p.id = :id")
//    Product getById(@Param("id") long id);
//
//    @Modifying
//    @Query("UPDATE Product as p SET p.name='$name',p.price='$price' where p.id = :id")
//    @Transactional
//    Product update(long id);
}
