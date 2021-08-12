package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
                (new Employee(2, "Suho", 30, "male", 900000)),
                (new Employee(4, "Kyungsoo", 19, "male", 1000000)),
                (new Employee(5, "Chanyeol", 26, "female", 1000000)),
                (new Employee(8, "Kai", 18, "female", 1000000)),
                (new Employee(65, "Xiumin", 25, "male", 150000)));
    }

    @Test
    void should_return_all_employee_when_call_get_employee_api() throws Exception {
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].id").isNumber())
                .andExpect(jsonPath("$[2].name").value("Kyungsoo"))
                .andExpect(jsonPath("$[2].age").value(19))
                .andExpect(jsonPath("$[2].gender").value("male"))
                .andExpect(jsonPath("$[2].salary").value(1000000));
    }

    @Test
    void should_create_new_employee_when_addEmployee_given_employee_information() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\": 66,\n" +
                "    \"name\": \"Jungkook\",\n" +
                "    \"age\": 25,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 100000\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jungkook"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(100000));

    }

}
