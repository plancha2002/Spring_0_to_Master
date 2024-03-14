package com.example;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;


    public List<Employee> findAll0(){
        return repo.findAll();
    }
    /*
    Esto se ejecutara en otro hilo
     */
    @Bulkhead(name = "findAll1" , type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "findAll1")
    public CompletableFuture<List<Employee>> findAll1(){
        /*
        findAll tiene un sleep de 5 segundos
         */
        return CompletableFuture.completedFuture(repo.findAll());
    }

    @Bulkhead(name = "findAll2" , type = Bulkhead.Type.THREADPOOL)
    @TimeLimiter(name = "findAll2", fallbackMethod = "defaultFindAll")
    public CompletableFuture<List<Employee>> findAll2(){
        /*
        findAll tiene un sleep de 5 segundos
         */
        return CompletableFuture.completedFuture(repo.findAll());
    }

    private CompletableFuture<List<Employee>> defaultFindAll(Throwable ex){
        System.out.println(ex.getMessage());
        System.out.println("defaultFindAll");
        CompletableFuture<List<Employee>> result = new CompletableFuture<>();
        result.complete(List.of());
        return result;

    }
}
