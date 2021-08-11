package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 1000));
        employees.add(new Employee(2, "Sehun", 26, "male", 1500));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 1500));
        employees.add(new Employee(4, "Xiumin", 25, "male", 1500));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 1500));
        employees.add(new Employee(6, "Chen", 27, "female", 1500));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 1500));
        employees.add(new Employee(8, "Lay", 29, "male", 1500));
        given(employeeRepository.getAllEmployees()).willReturn(employees);
//        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);


        //When
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //Then
        assertEquals(employees, actualEmployees);
    }
}
