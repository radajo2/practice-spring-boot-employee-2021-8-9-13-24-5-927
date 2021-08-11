package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
