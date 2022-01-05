package ru.gb.onlineshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.onlineshop.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long aLong);

    @Override
    Iterable<Role> findAll();
}
