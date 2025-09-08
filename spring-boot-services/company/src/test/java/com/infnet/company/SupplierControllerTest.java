package com.infnet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.company.model.Supplier;
import com.infnet.company.repository.SupplierRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SupplierControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private SupplierRepository repository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCreateSupplier() throws Exception {
        Supplier supplier = new Supplier(null, "TechSupplies", "contato@tech.com", "11999999999");

        mockMvc.perform(post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("TechSupplies"))
                .andExpect(jsonPath("$.email").value("contato@tech.com"))
                .andExpect(jsonPath("$.phone").value("11999999999"));
    }

    @Test
    void testGetSupplierById() throws Exception {
        Supplier supplier = repository.save(new Supplier(null, "GlobalParts", "suporte@global.com", "11888888888"));

        mockMvc.perform(get("/supplier/{id}", supplier.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("GlobalParts"))
                .andExpect(jsonPath("$.email").value("suporte@global.com"))
                .andExpect(jsonPath("$.phone").value("11888888888"));
    }

    @Test
    void testUpdateSupplier() throws Exception {
        Supplier supplier = repository.save(new Supplier(null, "OldSupplier", "old@email.com", "11777777777"));

        supplier.setName("NewSupplier");
        supplier.setEmail("new@email.com");
        supplier.setPhone("11666666666");

        mockMvc.perform(put("/supplier/{id}", supplier.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewSupplier"))
                .andExpect(jsonPath("$.email").value("new@email.com"))
                .andExpect(jsonPath("$.phone").value("11666666666"));
    }

    @Test
    void testDeleteSupplier() throws Exception {
        Supplier supplier = repository.save(new Supplier(null, "DeleteMe", "delete@me.com", "11555555555"));

        mockMvc.perform(delete("/supplier/{id}", supplier.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/supplier/{id}", supplier.getId()))
                .andExpect(status().isNotFound());
    }
}