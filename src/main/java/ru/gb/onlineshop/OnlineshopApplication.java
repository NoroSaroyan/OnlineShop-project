package ru.gb.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {},scanBasePackages = "ru.gb.*")
@ComponentScan("ru.gb.*")
public class OnlineshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineshopApplication.class, args);
    }
}

