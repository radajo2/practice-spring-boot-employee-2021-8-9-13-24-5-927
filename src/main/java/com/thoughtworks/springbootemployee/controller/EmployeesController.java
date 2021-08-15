package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.service.RetiringEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toResponseList(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.findById(employeeId));
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeesByGender(@RequestParam("gender") String employeeGender){
        return employeeMapper.toResponseList(employeeService.findByGender(employeeGender));
    }

    @GetMapping(params = {"pageIndex","pageSize"})
    public List<EmployeeResponse> getEmployeesByPage(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return  employeeMapper.toResponseList(employeeService.getEmployeesByPage(pageIndex, pageSize));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.addEmployee(employeeMapper.toEntity((employeeRequest))));
    }

    @PutMapping (path ="/{employeeId}")
    public EmployeeResponse updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest){
        return employeeMapper.toResponse(employeeService.updateEmployee(employeeId,employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping(path = "/{employeeId}")
    public Employee deleteEmployee(@PathVariable Integer employeeId){
        return employeeService.removeEmployee(employeeId);
    }
}