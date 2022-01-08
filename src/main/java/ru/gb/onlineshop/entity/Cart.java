package ru.gb.onlineshop.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity(name="carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Cart {
    @Id
    @GeneratedValue
    protected Long id;

    protected Long userId;
    //TODO targetEntity or mappedById
    @OneToMany(targetEntity = Product.class)
    protected List<Product> products;

}