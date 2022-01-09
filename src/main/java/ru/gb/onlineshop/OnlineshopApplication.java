package ru.gb.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {},scanBasePackages = "ru.gb.*")
public class OnlineshopApplication {
    //todo check registration,ADD PRODUCTS migration, fix product page ,add/delete product, fix cart
    public static void main(String[] args) {
        SpringApplication.run(OnlineshopApplication.class, args);
    }
}

