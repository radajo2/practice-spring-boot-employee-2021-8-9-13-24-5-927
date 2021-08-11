package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

//    private List<Employee> employees = new ArrayList<>();

//    public EmployeesController() {
//        employees.add(new Employee(1, "Suho", 30, "female", 1000));
//        employees.add(new Employee(2, "Sehun", 26, "male", 1500));
//        employees.add(new Employee(3, "Chanyeol", 28, "male", 1500));
//        employees.add(new Employee(4, "Xiumin", 25, "male", 1500));
//        employees.add(new Employee(5, "Baekhyun", 22, "male", 1500));
//        employees.add(new Employee(6, "Chen", 27, "female", 1500));
//        employees.add(new Employee(7, "Kyungsoo", 26, "male", 1500));
//        employees.add(new Employee(8, "Lay", 29, "male", 1500));
//    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {
        return employeeService.findById(employeeId);
    }

//    @GetMapping(params = {"page","pageSize"})
//    public List<Employee> getEmployeesByPage(@RequestParam(name="page", required = true) Integer page, @RequestParam(name = "pageSize", required = true) Integer pageSize) {
//        int skipvalue = (page-1)*pageSize;
//        return employees.stream()
//                .skip(skipvalue)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping(params = {"gender"})
//    public List<Employee> getEmployeesByGender(@RequestParam(name="gender", required = true) String gender){
//        return employees.stream()
//                .filter(employee -> employee.getGender().equals(gender))
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping
//    public void addEmployee(@RequestBody Employee employee) {
//        Employee employeeToBeAdded = new Employee(employees.size()+1,
//                employee.getName(),
//                employee.getAge(),
//                employee.getGender(),
//                employee.getSalary());
//        employees.add(employeeToBeAdded);
//    }
//
//    @PutMapping (path ="/{employeeId}")
//    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated){
//        return employees.stream()
//                .filter(employee -> employee.getId().equals(employeeId))
//                .findFirst()
//                .map(employee -> updateEmployeeInfo(employee, employeeToBeUpdated))
//                .get();
//    }
//
//    private Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
//        if (employeeToBeUpdated.getName() != null) {
//            employee.setName(employeeToBeUpdated.getName());
//        }
//        if (employeeToBeUpdated.getAge() != null) {
//            employee.setAge(employeeToBeUpdated.getAge());
//        }
//        if (employeeToBeUpdated.getGender() != null) {
//            employee.setGender(employeeToBeUpdated.getGender());
//        }
//        if (employeeToBeUpdated.getSalary() != null) {
//            employee.setSalary(employeeToBeUpdated.getSalary());
//        }
//        return employee;
//    }
}