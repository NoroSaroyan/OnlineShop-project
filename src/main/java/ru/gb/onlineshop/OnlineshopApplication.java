package ru.gb.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {},scanBasePackages = "ru.gb.*")
public class OnlineshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineshopApplication.class, args);
    }
}

