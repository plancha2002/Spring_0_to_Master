package com.example.service;

import com.example.domain.Product;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    Product deleteById(Long id);
}
