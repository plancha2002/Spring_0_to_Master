package com.example.service;

import com.example.domain.Product;
import com.example.error.ProductTitleException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public Product findById(Long id) {
        throw new NoSuchElementException("Product no founc");
    }

    @Override
    public Product save(Product product) {
        throw new ProductTitleException(product);
    }

    @Override
    public Product deleteById(Long id) {
        throw new NullPointerException();
    }
}
