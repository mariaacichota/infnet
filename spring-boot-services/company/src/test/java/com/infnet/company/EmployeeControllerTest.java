package com.infnet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.company.model.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    static String createdId;

    @Test
    @Order(1)
    void createEmployee() throws Exception {
        Employee e = new Employee(null, "John", "Doe", "john.doe@example.com", LocalDate.of(2020,1,1), 3500.0, "Developer");
        var res = mvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(e)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andReturn();

        String content = res.getResponse().getContentAsString();
        Employee created = mapper.readValue(content, Employee.class);
        createdId = created.getId().toString();
    }

    @Test
    @Order(2)
    void getEmployee() throws Exception {
        mvc.perform(get("/api/employee/{id}", createdId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    @Order(3)
    void updateEmployee() throws Exception {
        Employee e = new Employee(null, "John", "Doe", "john.doe@example.com", LocalDate.of(2020,1,1), 4000.0, "Senior Dev");
        mvc.perform(put("/api/employee/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(e)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(4000.0))
                .andExpect(jsonPath("$.position").value("Senior Dev"));
    }

    @Test
    @Order(4)
    void deleteEmployee() throws Exception {
        mvc.perform(delete("/api/employee/{id}", createdId))
                .andExpect(status().isNoContent());

        mvc.perform(get("/api/employee/{id}", createdId))
                .andExpect(status().isNotFound());
    }
}