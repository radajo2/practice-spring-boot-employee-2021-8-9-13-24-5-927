package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer Id) {
        return employeeRepository.findById(Id).orElse(null);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public List<Employee> getEmployeesByPage(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex-1,pageSize)).getContent();
    }

    public Employee addEmployee(Employee newEmployeeInfo) {
        return employeeRepository.save(newEmployeeInfo);
    }

    public Employee updateEmployee(Integer employeeId, Employee updatedEmployeeInfo) {
        Employee updateEmployee = employeeRepository.findById(employeeId)
                .map(employee -> updateEmployeeInfo(employee, updatedEmployeeInfo))
                .get();
        return employeeRepository.save(updateEmployee);
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
        if (employeeToBeUpdated.getName() != null) {
            employee.setName(employeeToBeUpdated.getName());
        }
        if (employeeToBeUpdated.getAge() != null) {
            employee.setAge(employeeToBeUpdated.getAge());
        }
        if (employeeToBeUpdated.getGender() != null) {
            employee.setGender(employeeToBeUpdated.getGender());
        }
        if (employeeToBeUpdated.getSalary() != null) {
            employee.setSalary(employeeToBeUpdated.getSalary());
        }
        return employee;
    }

    public Employee removeEmployee(Integer employeeId) {
        Optional<Employee> removeEmployee = employeeRepository.findById(employeeId);
        employeeRepository.deleteById(employeeId);
        return removeEmployee.orElse(null);
    }
}
