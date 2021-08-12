package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

//    @GetMapping(path = "/{employeeId}")
//    public Employee findById(@PathVariable Integer employeeId) {
//        return employeeService.findById(employeeId);
//    }
//
//    @GetMapping(params = {"gender"})
//    public List<Employee> getEmployeesByGender(@RequestParam("gender") String employeeGender){
//        return employeeService.findByGender(employeeGender);
//    }
//
//    @GetMapping(params = {"pageIndex","pageSize"})
//    public List<Employee> getEmployeesByPage(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
//        return  employeeService.getEmployeesByPage(pageIndex, pageSize);
//    }
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return employeeService.addEmployee(newEmployee);
    }

//    @PutMapping (path ="/{employeeId}")
//    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee updateEmployeeInfo){
//        return employeeService.updateEmployee(employeeId,updateEmployeeInfo);
//    }
//
//    @DeleteMapping(path = "/{employeeId}")
//    public Employee deleteEmployee(@PathVariable Integer employeeId){
//        return employeeService.removeEmployee(employeeId);
//    }
}