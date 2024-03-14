package com.example.controller;

import com.example.Repository.ProductRepository;
import com.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
        //el controlador va a escuchar en la url /products
@RequestMapping("/products")
public class ProductController {
//    @Autowired
//    se recomienda inyectar con el constructor pq facilita el testing
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //el get mapping estaria haciendo lo siguiente
    //GET http://localhost:8080/products
    @GetMapping
    //buscaria todos los productos
    public String findAll(Model model){
        List<Product> products = this.productRepository.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }


    /*
    GET http://localhost:8080/products/new
     */
    @GetMapping("/new")
    public String getForm(Model model){
        model.addAttribute("products", new Product());
        return "product-form";
    }

    /*
 POST http://localhost:8080/products
  */
    @PostMapping
    public String save(@ModelAttribute("product") Product product){
        this.productRepository.save(product);
        return "redirect:/products";
    }
}
