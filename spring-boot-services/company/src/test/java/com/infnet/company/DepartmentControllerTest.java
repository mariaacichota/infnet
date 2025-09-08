package com.infnet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.company.model.Department;
import com.infnet.company.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private DepartmentRepository repository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCreateDepartment() throws Exception {
        Department dept = new Department(null, "TI", "Tecnologia da Informação");

        mockMvc.perform(post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testGetDepartmentById() throws Exception {
        Department dept = repository.save(new Department(null, "RH", "Recursos Humanos"));

        mockMvc.perform(get("/department/{id}", dept.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("RH"))
                .andExpect(jsonPath("$.description").value("Recursos Humanos"));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Department dept = repository.save(new Department(null, "Financeiro", "Área de finanças"));
        dept.setDescription("Gestão Financeira");

        mockMvc.perform(put("/department/{id}", dept.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Gestão Financeira"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        Department dept = repository.save(new Department(null, "Marketing", "Promoções e campanhas"));

        mockMvc.perform(delete("/department/{id}", dept.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/department/{id}", dept.getId()))
                .andExpect(status().isNotFound());
    }
}
