package com.example.service;

import com.example.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//indicaremos los metodos que tendra nuestra logica de negocio
public interface ManufacturerService {

    //Retrieve - Read
    List<Manufacturer> findAll();
    List<Manufacturer> findAllByYear(Integer year);
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByName(String name);

    //Create - Update
    Manufacturer save(Manufacturer manufacturer);
    List<Manufacturer> saveAll(List<Manufacturer> manufacturers);

    // delete
    void deleteById(Long id);
    void deleteAll();

    boolean existById(Long id);

    // Más lógica de negocio:
    // 1. Coches fabricados
    // 2. Beneficios obtenidos
    // 3. .....



}

