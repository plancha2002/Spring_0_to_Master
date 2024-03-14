package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manufactrurers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "num_employees")
    private Integer numEmployees;

    @Column(name = "init_year")
    private Integer year;

    public Manufacturer() {
    }

    public Manufacturer(String name, Integer numEmployees, Integer year) {
        this.name = name;
        this.numEmployees = numEmployees;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numEmployees=" + numEmployees +
                ", year=" + year +
                '}';
    }
}
