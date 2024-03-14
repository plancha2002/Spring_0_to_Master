package com.example.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cif;

    /*
    Aqui es al contrario que en employee
    una compañia para varios empleados

    en mappedBy indicamos el nombre de la variable de donde esta la FK
     */
    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String cif, List<Employee> employees) {
        this.name = name;
        this.cif = cif;
        this.employees = employees;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cif='" + cif + '\'' +
                ", employees=" + employees +
                '}';
    }
}

