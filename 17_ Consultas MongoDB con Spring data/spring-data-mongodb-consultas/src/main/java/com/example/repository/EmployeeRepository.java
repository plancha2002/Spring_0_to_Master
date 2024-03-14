package com.example.repository;

import com.example.model.Employee;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findAllByMarriedTrue();
    List<Employee> findAllByMarriedFalse();
    List<Employee> findAllByName(String name);

    @Query(value = "{'name' :  ?0}")
    List<Employee> findAllByNameQuery(String name);

    List<Employee> findAllByBirthDateAfter(LocalDate date);
    @Query(value ="""
        {
            "birthDate": {
                "$gte":{
                    "$date": ?0
                }
            }
        }
    """ )
    List<Employee> findAllByBirthDateAfterQuery(LocalDate date);

    @Query(value ="""
        {
            "initDate": {
                "$gte":{
                    "$date": ?0
                }
            },
             "endDate": {
                "$lte":{
                    "$date": ?1
                }
            }
        }
    """ )
    List<Employee> findAllByInitAndEndDateQuery(LocalDate init, LocalDate end);

    @Query(value = """
    
        {
            "birthDate":{
                "$gte": ?0,
                "$lte": ?1
            }
        }
    
    
    """)
    List<Employee> findAllByBirthDateBetweenQuery(LocalDate minDate, LocalDate maxDate);

    //proyecciones
    @Query(value = "{}",fields = "{name: 1}")
    List<Employee> findAllIdAndName();

    @Query(value = "{}",fields = "{name: 1, email:  1}")
    List<Employee> findAllIdNameAndEmail();
    @Query(value = "{}",fields = "{name: 1, email:  1, _id: 0}")
    List<Employee> findAllNameEmailAndExcludingId();

    @Query(value = "{}", fields = "{_id: 0, birthDate: 0, salary: 0, married: 0, initDate: 0, endDate: 0}")
    List<Employee> findAllNameEmailAndExcludingIdTest();
}
