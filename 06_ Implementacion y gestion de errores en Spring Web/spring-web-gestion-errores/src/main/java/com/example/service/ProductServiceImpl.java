package com.example.service;

import com.example.model.Product;
import com.example.service.exception.ProductTitleException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService{
    @Override
    public Product findById(Long id) {
        throw new NoSuchElementException("Product no fund by id: "+ id);
    }

    @Override
    public Product save(Product p) {
        throw new ProductTitleException(p);
    }

    @Override
    public Product deleteById(Long id) {
        throw new NullPointerException();
    }
}
