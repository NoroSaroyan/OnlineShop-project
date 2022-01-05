package ru.gb.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //Todo check if join table is needed
    @ManyToMany(targetEntity = Product.class)
//    @JoinTable(
//            name = "category_product_relations",
//            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
//    )
    private List<Product> products;
}
