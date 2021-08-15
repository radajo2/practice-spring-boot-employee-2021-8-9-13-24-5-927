package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetiringCompanyService {
    @Resource
    private RetiringCompanyRepository retiringCompanyRepository;

    public RetiringCompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompanies() {
        return retiringCompanyRepository.getCompanies();
    }

    public Company findById(Integer companyId) {
        return getAllCompanies()
                .stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getAllEmployeesByCompanyId(Integer companyId) {
        return findById(companyId).getEmployees();
    }

    public List<Company> getCompaniesByPage(Integer pageIndex, Integer pageSize) {
        return getAllCompanies()
                .stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
