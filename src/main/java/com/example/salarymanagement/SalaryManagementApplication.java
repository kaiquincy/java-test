package com.example.salarymanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.salarymanagement.entity.Employee;
import com.example.salarymanagement.repository.EmployeeRepository;

@SpringBootApplication
public class SalaryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(EmployeeRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Employee(null, "Bob", 30, 12000));
                repo.save(new Employee(null, "Silvia", 42, 15000));
            }
        };
    }
}
