package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RetiringCompanyRepository {

    private List<Company> companies = new ArrayList<>();

    public RetiringCompanyRepository () {
        companies.add(new Company(1,"SM Entertainment", Arrays.asList(
                new Employee(1, "Joanna", 20, "female", 100),
                new Employee(2, "Suho", 25, "male", 900000),
                new Employee(4, "Kyungsoo", 19, "male", 1000000),
                new Employee(5, "Chanyeol", 26, "female", 1000000),
                new Employee(6, "Kai", 18, "female", 1000000),
                new Employee(7, "Xiumin", 25, "male", 150000))));
        companies.add(new Company(2, "Big Hit Entertainment", Arrays.asList(
                new Employee(1,"Jungkook",27,"male",50000),
                new Employee(2,"Taehyung",25,"male",100000),
                new Employee(3,"Jin",25,"male",600000))));
    }
    public List<Company> getCompanies(){
        return companies;
    }

}
