package repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees;

    public EmployeeRepository() {}

    public List<Employee> getEmployees() {
        return employees;
    }
}
