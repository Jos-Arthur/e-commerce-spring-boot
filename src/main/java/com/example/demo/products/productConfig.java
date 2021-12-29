package com.example.demo.products;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class productConfig {

    @Bean
    CommandLineRunner commandLineRunner (ProductRepository repository) {
        return args -> {
            Product product1 = new Product(
                    "test1",
                    "test1",
                    100f
            );
            repository.save(product1);
        };
    }
}
