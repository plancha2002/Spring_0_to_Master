package com.example.repository;

import com.example.model.Employee;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

//AQUI LAS CONSULTAS
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findByAgeIn(List<Integer> ages);
    List<Employee> findByAge(Integer age);
    List<Employee> findByAgeBetween(Integer min, Integer max);
    List<Employee> findByAgeOrderByFirstNameDesc(Integer age);

    long countByAge(Integer age);
    long countByAgeAfter(Integer age);
    long countByAgeBefore(Integer age);

    //con query hacemos que nosotros asignemos la consulta
    @Query("select e from Employee e where e.age in :ages")
    List<Employee> findByAgeInQuery(List<Integer> ages);

    @Query("""
        SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END 
        FROM Employee e
        WHERE lower(e.firstName) LIKE lower(:firstName) 
    """)
    boolean existsByEmailLikeQuery(String firstName);

    @Query(value = "SELECT * FROM employees e WHERE e.married = true", nativeQuery = true )
    List<Employee> findAllByMarriedTrueQuery();

    @Transactional
    @Modifying
    @Query("update Employee e SET e.married = false WHERE e.birthDate > :birthDate")
    void updateMarriedToFalseByBirthDateQuery(LocalDate birthDate);





}
