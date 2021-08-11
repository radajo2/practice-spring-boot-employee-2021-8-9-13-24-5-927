package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(2, "David", 23, "male", 1500));
        employees.add(new Employee(3, "David", 23, "male", 1500));
        employees.add(new Employee(4, "David", 23, "male", 1500));
        employees.add(new Employee(5, "David", 23, "male", 1500));
        employees.add(new Employee(6, "Joanna", 18, "female", 1500));
        employees.add(new Employee(7, "David", 23, "male", 1500));
        employees.add(new Employee(8, "David", 23, "male", 1500));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

    }

    @GetMapping(params = {"page","pageSize"})
    public List<Employee> getEmployeesByPage(@RequestParam(name="page", required = true) Integer page, @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        int skipvalue = (page-1)*pageSize;
        return employees.stream()
                .skip(skipvalue)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam(name="gender", required = true) String gender){
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        Employee employeeToBeAdded = new Employee(employees.size()+1,
                employee.getName(),
                employee.getAge(),
                employee.getGender(),
                employee.getSalary());
        employees.add(employeeToBeAdded);
    }
}