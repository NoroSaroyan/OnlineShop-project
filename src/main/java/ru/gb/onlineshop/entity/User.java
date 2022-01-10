package ru.gb.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_relations",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false,updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id",nullable = false,updatable = false)
    )
    private List<Role> roles;

    public User() {

    }
}

