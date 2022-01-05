package ru.gb.onlineshop.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Cart {
    @Id
    @GeneratedValue
    protected Long id;

    protected Long productCount;

    @ManyToOne(targetEntity = Product.class)
    protected Product product;
}