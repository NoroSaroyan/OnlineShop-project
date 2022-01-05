package ru.gb.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    //TODO check if mappedBy is needed
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {

    }
}
