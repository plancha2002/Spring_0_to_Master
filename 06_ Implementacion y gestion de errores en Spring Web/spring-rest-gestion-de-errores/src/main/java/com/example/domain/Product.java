package com.example.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Product {

    
    private Long id;

    @Size(
            min=10,
            max=100
    )
    @NotNull(message = "titulo obligatorio")
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "fecha obligatorio")
    private LocalDate released;
    @Min(value = 5)
    @NotNull(message = "precio obligatorio")
    private Double price;

    @Valid
    private Manufacturer manufacturer;

    public Product() {
    }

    public Product(String title, LocalDate released, Double price, Manufacturer manufacturer) {
        this.title = title;
        this.released = released;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", released=" + released +
                ", price=" + price +
                '}';
    }
}
