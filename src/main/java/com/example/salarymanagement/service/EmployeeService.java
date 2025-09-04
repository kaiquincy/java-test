package com.example.salarymanagement.service;

import com.example.salarymanagement.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee save(Employee employee);
    Employee getById(Long id);
    void deleteById(Long id);
    List<Employee> searchByName(String name);
    boolean isNameTaken(String name, Long excludeId);
}
