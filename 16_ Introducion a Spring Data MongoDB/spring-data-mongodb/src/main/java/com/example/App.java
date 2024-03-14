package com.example;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(App.class, args);

        var repo = context.getBean(EmployeeRepository.class);

        repo.deleteAll();
        List<Employee> employees = List.of(

                new Employee("emp1", "emp1@gmail.com", LocalDate.of(1990,1,1)),
                new Employee("emp2", "emp2@gmail.com", LocalDate.of(1990,1,1)),
                new Employee( "emp3", "emp3@gmail.com", LocalDate.of(1990,1,1)),
                new Employee( "emp4", "emp4@gmail.com", LocalDate.of(1990,1,1)),
                new Employee( "emp5", "emp5@gmail.com", LocalDate.of(1990,1,1))


        );

        repo.saveAll(employees);

        repo.findAll().forEach(System.out::println);

        System.out.println("count: " +repo.countAllByName("emp5"));

        var mongoTemplate = context.getBean(MongoTemplate.class);

        Employee emp6 = new Employee("emp6", "emp4@gmail.com", LocalDate.of(1990,1,1));
        mongoTemplate.save(emp6);

        //requiere un objeto query (que venga de mongo)
//        Query query = new Query();
//        query.addCriteria(Criteria.where())
//        mongoTemplate.findAndModify()
    }

}
