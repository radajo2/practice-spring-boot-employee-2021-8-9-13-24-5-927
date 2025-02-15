package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> testEmployees;

    @BeforeEach
    public void setup(){
        testEmployees = Arrays.asList
                (new Employee(1, "Joanna", 20, "female", 100),
                (new Employee(2, "Suho", 25, "male", 900000)),
                (new Employee(4, "Kyungsoo", 19, "male", 1000000)),
                (new Employee(5, "Chanyeol", 26, "female", 1000000)),
                (new Employee(8, "Kai", 18, "female", 1000000)),
                (new Employee(65, "Xiumin", 25, "male", 150000)));
                employeeRepository.saveAll(testEmployees);
    }

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employee_when_call_get_employee_api() throws Exception {
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].name").value("Kyungsoo"))
                .andExpect(jsonPath("$[2].age").value(19))
                .andExpect(jsonPath("$[2].gender").value("male"))
                .andExpect(jsonPath("$[2].salary").value(1000000))
                .andExpect(jsonPath("$[3].name").value("Chanyeol"));
    }

    @Test
    void should_return_employee_when_findById_given_employee_id() throws Exception {
        final Employee employee = new Employee(50, "Joanna", 25, "female", 1000);
        final Employee savedEmployee = employeeRepository.save(employee);

        //when
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joanna"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(1000));
    }

    @Test
    void should_return_employees_when_findByGender_given_employee_gender() throws Exception {
        String gender = "male";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("gender", gender)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].gender", Matchers.hasItems("male")));
    }

    @Test
    void should_return_five_employees_per_list_when_getListByPage_given_pageIndex_is_1_and_pageSize_is_5() throws Exception {
        int pageSize = 5;
        int pageIndex = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("pageIndex", String.valueOf(pageIndex)).param("pageSize", String.valueOf(pageSize))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$[0].name").value("Joanna"));
    }

    @Test
    void should_create_new_employee_when_addEmployee_given_employee_information() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\": 100,\n" +
                "    \"name\": \"Taehyung\",\n" +
                "    \"age\": 23,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 100000\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Taehyung"))
                .andExpect(jsonPath("$.age").value(23))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(100000));

    }

    @Test
    void should_update_employee_info_when_updateEmployee_given_employee_information() throws Exception {
        //given
        final Employee employee = new Employee(100, "Hotdog", 25, "female", 1000);
        final Employee savedEmployee = employeeRepository.save(employee);
        String newEmployeeInfo = "{\n" +
                "    \"id\": 100,\n" +
                "    \"name\": \"Taehyung\",\n" +
                "    \"age\": 23,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 100000\n" +
                "}";

        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value("23"));
    }

    @Test
    void should_remove_when_removeEmployee_given_employee_id() throws Exception {
        //given
        final Employee employee = new Employee(100, "Hotdog", 25, "female", 1000);
        final Employee savedEmployee = employeeRepository.save(employee);

        //when
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
