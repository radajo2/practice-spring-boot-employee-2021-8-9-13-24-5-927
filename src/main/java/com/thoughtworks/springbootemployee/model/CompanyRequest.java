package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class CompanyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String company_name;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employees = new LinkedList<>();

    public CompanyRequest() {}

    public CompanyRequest(String name, List<Employee> employees) {
        this.company_name = name;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
