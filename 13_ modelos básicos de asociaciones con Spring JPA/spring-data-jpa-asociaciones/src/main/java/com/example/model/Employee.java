package com.example.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 300)
    private String name;

    private Integer age;

    private Boolean married;

    private Double salary;

    private Instant createdDate;

    //fecha
    private LocalDate birthDate;

    //fecha y hora
    private LocalDateTime registractionDate;

    //se almacena en otra tabla
    @ElementCollection
    private List<String> email = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EmployeeCategory category;

    //caso: un empleado solo tiene una vivienda y una vivienda solo tiene un empleado
    @OneToOne
    @JoinColumn(name = "id_address",
                foreignKey = @ForeignKey(name = "fk_employee_address")
    )
    private Address address;

    /*
    como empleado pertenezco a una empresa
    EN ESTE CASO
    Una empresa puede tener muchos empleados
    Un empleado solo puede tener una empresa

    Muchos Pueden a uno ( many to one)

     */
    @ManyToOne
    @JoinColumn(name = "id_company", foreignKey = @ForeignKey(name = "fk_employee_company"))
    private Company company;

    /*
    un empleado puede trabajar en uno o mas proyecto (many)
    un proyecto puede tener varios empleados (many)

    Para esta relacion no sera suficiente con un columnas, haremos una tabal separada
     */
    @ManyToMany
    // el join table no es obligatorio, con el podremos manipular los nombres
    @JoinTable(name = "employee_projects",
            //name = nombre en la nueva tabla. referencedColumnName = nombre del id en su tabla
            //join columns hara referencia a esta tabla (empleado)
            joinColumns = @JoinColumn(name = "id_employee" , referencedColumnName = "id"),
            //inverse join column hara referencia a la table con que nos relacionamos (proyecto)
            inverseJoinColumns = @JoinColumn(name = "id_project" , referencedColumnName = "id")
    )
    private List<Project> projects = new ArrayList<>();

    public Employee() {
    }

    //en este caso seria conveniente usar algun framework como lombok para hacer mas legible el codigo
    public Employee(String name, Integer age, Boolean married, Double salary, Instant createdDate, LocalDate birthDate, LocalDateTime registractionDate, List<String> email, EmployeeCategory category, Address address, Company company, List<Project> projects) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.salary = salary;
        this.createdDate = createdDate;
        this.birthDate = birthDate;
        this.registractionDate = registractionDate;
        this.email = email;
        this.category = category;
        this.address = address;
        this.company = company;
        this.projects = projects;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getRegistractionDate() {
        return registractionDate;
    }

    public void setRegistractionDate(LocalDateTime registractionDate) {
        this.registractionDate = registractionDate;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public EmployeeCategory getCategory() {
        return category;
    }

    public void setCategory(EmployeeCategory category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", married=" + married +
                ", salary=" + salary +
                ", createdDate=" + createdDate +
                ", birthDate=" + birthDate +
                ", registractionDate=" + registractionDate +
                ", email=" + email +
                ", category=" + category +
                ", address=" + address +
                ", company=" + company +
                ", projects=" + projects +
                '}';
    }
}


