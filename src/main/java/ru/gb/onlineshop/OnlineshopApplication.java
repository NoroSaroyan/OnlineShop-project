package ru.gb.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {},scanBasePackages = "ru.gb.*")
public class OnlineshopApplication {
    //todo add/delete product
    public static void main(String[] args) {
        SpringApplication.run(OnlineshopApplication.class, args);
    }
}

