package com.example.service;

import com.example.model.Product;

public interface ProductService {
    Product findById(Long id);
    Product save(Product p);
    Product deleteById(Long id);
}
