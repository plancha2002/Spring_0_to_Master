package com.example;

import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees/{id}")
    public Callable<Employee> findById(@PathVariable Long id){

        return () -> {
            Thread.sleep(5000L); //simulamos un retraso de tiempo
            return new Employee(1L, "", 4000.0);
        };

    }

    @GetMapping("/employees0")
    public List<Employee> findAll0(){
        return service.findAll0();
    }

    /*
    en principio este daria error po un time out. Esta configuracion esta en
    application.yml.
    LA configuracion de este metodo conrrespode findAll1, lo podemos ver en
    @TimeLimiter en el EmployeeService
     */
    @GetMapping("/employees1/prueba1")
    public CompletableFuture<List<Employee>> findAll1(){
        return service.findAll1();
    }

    @GetMapping("/employees1/prueba2")
    public List<Employee> findAll1Block() throws ExecutionException, InterruptedException, TimeoutException {
        return service.findAll1().get(3, TimeUnit.SECONDS);
    }

    @GetMapping("/employees1/prueba3")
    public CompletableFuture<List<Employee>> findAll1WhenComplete() {
        return service.findAll1().whenComplete((e, ex) ->{
            if (e != null){
                System.out.println(e);   
            }

            if (ex!=null) {
                System.out.println(ex.getMessage());
            }
        });
    }

    @GetMapping("/employees2")
    public CompletableFuture<List<Employee>> findAll2() throws ExecutionException, InterruptedException, TimeoutException {
        return service.findAll2();
    }


}
