package com.thoughtworks.springbootemployee.model;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;

public class CompanyResponse {
    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees = new LinkedList<>();

    public CompanyResponse() {}

    public CompanyResponse(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getCompany_name() {
        return companyName;
    }

    public void setCompany_name(String company_name) {
        this.companyName = company_name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
