package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiringEmployeeService {
    @Autowired
    private RetiringEmployeeRepository retiringEmployeeRepository;

    public RetiringEmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
//       return employeeRepository.findAll();
        return retiringEmployeeRepository.getEmployees();
    }

    public Employee findById(Integer Id) {
//        return employeeRepository.findById(Id).orElse(null);
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(Id))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findByGender(String gender) {
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPage(Integer pageIndex, Integer pageSize) {
        int skipValue = ((pageIndex - 1) * pageSize);
        return getAllEmployees()
                .stream()
                .skip(skipValue)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee newEmployeeInfo) {
//        employeeRepository.save(newEmployeeInfo);
//        return newEmployeeInfo;
        Employee employeeToBeAdded = new Employee(retiringEmployeeRepository.getEmployees().size()+1,
                newEmployeeInfo.getName(),
                newEmployeeInfo.getAge(),
                newEmployeeInfo.getGender(),
                newEmployeeInfo.getSalary());
        retiringEmployeeRepository.getEmployees().add(employeeToBeAdded);
        return employeeToBeAdded;
    }

    public Employee updateEmployee(Integer employeeId, Employee updatedEmployeeInfo) {
        return getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, updatedEmployeeInfo))
                .get();
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
        Employee removeEmployee = retiringEmployeeRepository.getEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
        retiringEmployeeRepository.getEmployees().remove(removeEmployee);

        return removeEmployee;
    }
}

