package com.example;

import com.example.Repository.ProductRepository;
import com.example.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        var repository = context.getBean(ProductRepository.class);

        List<Product> products = List.of(
                new Product("producto1", 5.99, 1),
                new Product("producto2", 6.99, 2),
                new Product("producto3", 7.99, 3),
                new Product("producto4", 8.99, 4),
                new Product("producto5", 9.99, 5),
                new Product("producto6", 10.99, 5)

        );
        repository.saveAll(products);
    }

}
