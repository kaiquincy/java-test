package com.example.salarymanagement.service.impl;

import com.example.salarymanagement.entity.Employee;
import com.example.salarymanagement.repository.EmployeeRepository;
import com.example.salarymanagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Employee> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean isNameTaken(String name, Long excludeId) {
        if (excludeId == null) {
            return repository.existsByNameIgnoreCase(name);
        }
        return repository.existsByNameIgnoreCaseAndIdNot(name, excludeId);
    }
}
