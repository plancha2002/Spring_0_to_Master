package com.example.error;

import com.example.domain.Product;

public class ProductTitleException extends RuntimeException {
    private Product product;
    public ProductTitleException(Product product) {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
