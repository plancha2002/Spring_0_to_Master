package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }
    @GetMapping()
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(this.repo.findAll());
    }

    /*
    Si no ponemos nada tomaria lo siguiente
    GET http://localhost:8080/api/employees/sorted?sort=id,desc
    GET http://localhost:8080/api/employees/sorted?sort=name,desc
    GET http://localhost:8080/api/employees/sorted?sort=name,desc&salary,desc
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<Employee>> findAllSorted(
                                            @RequestParam(defaultValue = "id,desc") String[] sort
    ){
        List<Sort.Order> orders = extractOders(sort);
        return ResponseEntity.ok(this.repo.findAll(Sort.by(orders)));
    }
    /*
   GET http://localhost:8080/api/employees/paged
   GET http://localhost:8080/api/employees/paged?pageNumber=0&pageSize=3&sort=name,desc
   GET http://localhost:8080/api/employees/paged?pageNumber=1&pageSize=2&sort=name,desc&salary,asc
    */
    @GetMapping("/paged")
    public ResponseEntity<List<Employee>> findAllSortedAndPaged(
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize
    ){
        List<Sort.Order> orders = extractOders(sort);
                                            // numPagina, elementosPag?
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(orders));
        Page<Employee> page = this.repo.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> findAllSortedAndPagedInfo(
            @RequestParam(defaultValue = "id,desc") String[] sort,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize
    ){
        List<Sort.Order> orders = extractOders(sort);
        // numPagina, elementosPag?
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(orders));
        Page<Employee> page = this.repo.findAll(pageable);
        var info =  Map.of(
                "employees", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItem", page.getTotalElements(),
                "totalPages", page.getTotalPages()
        );
        return ResponseEntity.ok(info);
    }
    /*

    1 order:
    [0]: id
    [1]: desc

    múltiples order
    [0]: id,desc
    [1]: name,asc
    [2]: email,desc

     */
    private List<Sort.Order> extractOders(String[] sort) {

        if (sort[0].contains(",")){
            //map(pair ->extractOder(pair))
           return Arrays.stream(sort).map(this::extractOder).toList();
        }

       return List.of(extractOder(sort[0]+","+sort[1]));
    }

    private Sort.Order extractOder(String s) {
        System.out.println(s);
        String[] pair = s.split(",");
        String field = pair[0];

        Sort.Direction direction = pair[1].equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
                    //si                //si no
        return new Sort.Order(direction, field);
    }
}
