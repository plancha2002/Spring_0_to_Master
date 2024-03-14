package com.example;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        var empRepo = context.getBean(EmployeeRepository.class);

        List<Employee> employees = List.of(
                new Employee("employee1", LocalDate.now(), true),
                new Employee("employee2", LocalDate.now(), false),
                new Employee("employee3", LocalDate.now(), true),
                new Employee("employee4", LocalDate.now(), false),
                new Employee("employee5", LocalDate.now(), true)
        );
        empRepo.saveAll(employees);

        empRepo.findAllByMarriedTrue().forEach(employee -> {
            System.out.println(employee.toString());
        });
    }

}
