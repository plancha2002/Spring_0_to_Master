package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
//el @Repository es opcional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // optional solo devulve uno
    Optional<Employee> findByFullName(String fullName);
    Optional<Employee> findByFullNameAndBirthDate(String fullname, LocalDate birthDate);

    //mas de un resultado
    List<Employee> findAllByBirthDateAfter(LocalDate birthDate);

    List<Employee> findAllByBirthDateBetween(LocalDate min, LocalDate max);

    List<Employee> findAllByMarriedTrue();
    List<Employee> findAllByMarriedFalse();




}
