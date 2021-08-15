package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findById(Integer companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException());
    }

    public List<Employee> getAllEmployeesByCompanyId(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public List<Company> getCompaniesByPage(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex-1,pageSize)).getContent();
    }

    public Company addCompany(Company companyInfo) {
        return companyRepository.save(companyInfo);
    }

    public Company updateCompany(Integer companyId, Company companyInfo) {
        Company updateCompany = companyRepository
                .findById(companyId)
                .map(company -> updateCompanyInfo(company, companyInfo))
                .get();
        return companyRepository.save(updateCompany);
    }

    private Company updateCompanyInfo(Company company, Company companyInfo) {
        if (companyInfo.getCompany_name() != null){
            company.setCompany_name(companyInfo.getCompany_name());
        }
        if (!companyInfo.getEmployees().isEmpty() && companyInfo.getEmployees() != null){
            company.setEmployees(companyInfo.getEmployees());
        }
        return company;
    }

    public Company removeCompany(Integer companyId) {
        Optional<Company> removeCompany = companyRepository.findById(companyId);
        companyRepository.deleteById(companyId);
        return removeCompany.orElseThrow(() -> new CompanyNotFoundException());
    }
}
