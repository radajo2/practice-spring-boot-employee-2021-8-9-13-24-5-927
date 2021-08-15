package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RetiringCompanyServiceTest {
    @InjectMocks
    private RetiringCompanyService retiringCompanyService;

    @Mock
    private RetiringCompanyRepository retiringCompanyRepository;

    private List<Company> testCompanies = new ArrayList<>();

    public RetiringCompanyServiceTest() {
    }

    @BeforeEach
    public void setup(){
        testCompanies.add(new Company(1,"SM Entertainment", Arrays.asList(
                new Employee(1, "Joanna", 20, "female", 100),
                new Employee(2, "Suho", 25, "male", 900000),
                new Employee(4, "Kyungsoo", 19, "male", 1000000),
                new Employee(5, "Chanyeol", 26, "female", 1000000),
                new Employee(6, "Kai", 18, "female", 1000000),
                new Employee(7, "Xiumin", 25, "male", 150000))));
        testCompanies.add(new Company(2, "Big Hit Entertainment", Arrays.asList(
                new Employee(1,"Jungkook",27,"male",50000),
                new Employee(2,"Taehyung",25,"male",100000),
                new Employee(3,"Jin",25,"male",600000))));
    }

    @Test
    void should_return_all_companies_when_getAllCompanies_given_all_companies() {
        //given
        given(retiringCompanyRepository.getCompanies()).willReturn(testCompanies);

        //when
        List<Company> actualCompanies = retiringCompanyService.getAllCompanies();

        //then
        assertEquals(testCompanies.size(), actualCompanies.size());
    }

    @Test
    void should_return_specific_company_when_findById_given_company_id() {
        //given
        given(retiringCompanyRepository.getCompanies()).willReturn(testCompanies);

        //when
        Company actualCompany = retiringCompanyService.findById(2);

        //then
        assertEquals(testCompanies.get(1).getId(), actualCompany.getId());
    }


}
