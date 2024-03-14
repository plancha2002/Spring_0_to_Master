package com.example.controller;

import com.example.model.Manufacturer;
import com.example.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// no es igual que controller
@RequestMapping("/api")
public class ManufacturerController {
    private ManufacturerService service;

    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }
    /*
    GET http://localhost:8080/api/manufacturers
     */

    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> findAll(){
        var manufacturers = this.service.findAll();
        if (manufacturers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(manufacturers);
    }

    /*
    GET http://localhost:8080/api/manufacturers/year/xxxx
    xxxx= es igual al año
     */
    @GetMapping("/manufacturers/year/{year}")
    public ResponseEntity<List<Manufacturer>> findAllByYear(@PathVariable Integer year){
        var manufacturers = this.service.findAllByYear(year);
        if (manufacturers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(manufacturers);
    }

    /*
    GET http://localhost:8080/api/manufacturers/id/x
    x= es igual al id
     */
    @GetMapping("/manufacturers/id/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer){
        if(manufacturer.getId() != null){
            ResponseEntity.badRequest().build();
        }
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> update(@RequestBody Manufacturer manufacturer){
        this.service.save(manufacturer);
        return ResponseEntity.ok(manufacturer);
    }

    @DeleteMapping("/manufacturers")
    public ResponseEntity<Manufacturer> deleteAll(){
        this.service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> deleteById(@PathVariable("id") Long id){
        if (this.service.existById(id)){
            this.service.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().build();
        }


    }


}
