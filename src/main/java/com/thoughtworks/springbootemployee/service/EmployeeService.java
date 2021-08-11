package com.thoughtworks.springbootemployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee findById(Integer Id) {
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(Id))
                .findFirst()
                .orElse(null);
    }
}
