package com.example.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String postalCode;

    //aqui indicaremos el owner que es el que almacena la fk
    //el hacer esta bi direccionalidad es opcional
    //, fetch = FetchType.LAZY
    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address() {
    }

    public Address(String street, String postalCode, Employee employee) {
        this.street = street;
        this.postalCode = postalCode;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", employee=" + employee +
                '}';
    }
}
