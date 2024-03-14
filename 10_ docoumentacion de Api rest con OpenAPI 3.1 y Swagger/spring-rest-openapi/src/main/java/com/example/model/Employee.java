package com.example.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class Employee {

    @Schema(example = "4",
            description = "Identificador de empleado. Clave primaria numerica auto incrementable"
            )
    private Long id;
    @Schema(example = "Eduardo",
            description = "Nombre completo de empleado")
    private String name;
    @Schema(example = "2022-01-01",
            description = "Fecha de nacimiento aaaa-mm-dd")
    private LocalDate brithDate;

    @Schema(example = "1555.55",
            description = "Salario mensual bruto empleado")
    private Double salary;
    @Schema(example = "true",
            description = "Situacion de casado o no")
    private Boolean married;

    public Employee() {
    }

    public Employee(Long id, String name, LocalDate brithDate, Double salary, Boolean married) {
        this.id = id;
        this.name = name;
        this.brithDate = brithDate;
        this.salary = salary;
        this.married = married;
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

    public LocalDate getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(LocalDate brithDate) {
        this.brithDate = brithDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brithDate=" + brithDate +
                ", salary=" + salary +
                ", married=" + married +
                '}';
    }
}

