package com.example.service;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

//    @Autowired
    private final ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Manufacturer> findAllByYear(Integer year) {
        Objects.requireNonNull(year);
        return this.repository.findAllByYear(year);
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return this.repository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> saveAll(List<Manufacturer> manufacturers) {
        return this.repository.saveAll(manufacturers);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public boolean existById(Long id) {
       return this.repository.existsById(id);
    }
}
