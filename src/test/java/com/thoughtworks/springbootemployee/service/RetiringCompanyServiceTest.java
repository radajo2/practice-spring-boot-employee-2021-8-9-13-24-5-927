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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void should_return_all_employees_when_getAllEmployeesByCompanyId_given_company_id() {
        //given
        given(retiringCompanyRepository.getCompanies()).willReturn(testCompanies);

        //when
        List<Employee> actualEmployeeList = retiringCompanyService.getAllEmployeesByCompanyId(2);

        //then
        assertEquals(testCompanies.get(1).getEmployees(), actualEmployeeList);
    }

    @Test
    void should_return_one_company_per_list_when_getCompaniesByPage_given_pageIndex_is_1_and_pageSize_is_1() {
        //given
        given(retiringCompanyRepository.getCompanies()).willReturn(testCompanies);
        int mockCount = 1;

        //when
        int actualCount = retiringCompanyService.getCompaniesByPage(1, 1).size();

        //then
        assertEquals(mockCount, actualCount);
    }

    @Test
    void should_return_new_company_when_addCompany_given_company_info() {
        //given
        List<Company> companies = new ArrayList<>();
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);
        Company newCompany = new Company(){{
            setCompany_name("JYP Entertainment");
            setEmployees(Arrays.asList(new Employee(1, "Joanna", 23, "female", 100000)));
        }};

        //when
        retiringCompanyService.addCompany(newCompany);

        //then
        assertEquals("JYP Entertainment", newCompany.getCompany_name());
    }

    @Test
    void should_update_existing_company_when_updateCompany_given_company_info() {
        //given
        given(retiringCompanyRepository.getCompanies()).willReturn(testCompanies);
        Company updateCompany = new Company(){{
            setCompany_name("YG Entertainment");
            setEmployees(Arrays.asList(new Employee()));
        }};

        //when
        Company updatedCompanyInfo = retiringCompanyService.updateCompany(1, updateCompany);

        //then
        assertEquals(updatedCompanyInfo.getCompany_name(), updateCompany.getCompany_name());
    }

    @Test
    void should_remove_existing_company_when_removeCompany_given_company_id() {
        //given
        List<Company> companies = new ArrayList<>();
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
        given(retiringCompanyRepository.getCompanies()).willReturn(companies);

        //when
        Company deletedCompany = retiringCompanyService.removeCompany(2);

        //then
        assertNotNull(deletedCompany);
        assertEquals(1, companies.size());
    }

}
