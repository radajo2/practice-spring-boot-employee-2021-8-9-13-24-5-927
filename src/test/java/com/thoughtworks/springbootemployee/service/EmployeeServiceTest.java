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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    void should_return_specific_employee_when_findById_given_employee_id() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        Employee actualEmployee = employeeService.findById(5);

        //then
        assertEquals(employees.get(4), actualEmployee);
    }

    @Test
    void should_return_specific_employee_when_findByGender_given_employee_gender() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        List<Employee> actualEmployees = employeeService.findByGender("male");

        //then
        assertEquals(6, actualEmployees.stream().map(Employee::getGender).filter(employeeGender -> employeeGender.equals("male")).count());
    }

    @Test
    void should_return_five_employee_per_list_when_getListByPage_given_pageIndex_is_1_and_pageSize_is_5() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        given(employeeRepository.getEmployees()).willReturn(employees);
        int mockEmployeeCount = 5;

        //when
        int actualCount = employeeService.getEmployeesByPage(1, 5).size();

        //then
        assertEquals(mockEmployeeCount, actualCount);
    }

    @Test
    void should_return_new_employee_when_addEmployee_given_employee_info() {
        //given
        List<Employee> employees = new ArrayList<>();
        given(employeeRepository.getEmployees()).willReturn(employees);
        Employee newEmployee = new Employee(){{
            setName("Jungkook");
            setAge(23);
            setGender("male");
            setSalary(150000);
        }};

        //when
        employeeService.addEmployee(newEmployee);

        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_update_existing_employee_when_updateEmployee_given_employee_info() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        given(employeeRepository.getEmployees()).willReturn(employees);
        Employee updateEmployee = new Employee(){{
            setAge(21);
        }};

        //when
        Employee updatedEmployeeInfo = employeeService.updateEmployee(2, updateEmployee);

        //then
        assertEquals(updatedEmployeeInfo.getAge(), updateEmployee.getAge());
    }
    @Test
    void should_remove_existing_employee_when_removeEmployee_given_employee_id() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Suho", 30, "female", 100000));
        employees.add(new Employee(2, "Sehun", 26, "male", 105000));
        employees.add(new Employee(3, "Chanyeol", 28, "male", 20000));
        employees.add(new Employee(4, "Xiumin", 25, "male", 70000));
        employees.add(new Employee(5, "Baekhyun", 22, "male", 80000));
        employees.add(new Employee(6, "Chen", 27, "female", 900000));
        employees.add(new Employee(7, "Kyungsoo", 26, "male", 600000));
        employees.add(new Employee(8, "Lay", 29, "male", 900000));
        employees.add(new Employee(9, "Jungkook", 20, "male", 500000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        //when
        Employee deletedEmployee = employeeService.removeEmployee(1);

        //then
        assertNotNull(deletedEmployee);
        assertEquals(8, employees.size());
    }
}
