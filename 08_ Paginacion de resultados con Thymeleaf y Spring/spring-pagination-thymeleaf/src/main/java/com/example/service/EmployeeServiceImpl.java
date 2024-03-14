package com.example.service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
   private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Page<Employee> findAllPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
//        if(sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())){
//            Sort.by(sortField).ascending();
//        }else{
//            Sort.by(sortField).descending();
//
//        }
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.repository.findAll(pageable);

        }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
