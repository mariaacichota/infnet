package com.infnet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.company.model.Customer;
import com.infnet.company.repository.CustomerRepository;
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
class CustomerControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private CustomerRepository repository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer(null, "Ana Silva", "ana@email.com", "11911111111", "Rua A, 123");

        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("Ana Silva"))
                .andExpect(jsonPath("$.email").value("ana@email.com"))
                .andExpect(jsonPath("$.phone").value("11911111111"))
                .andExpect(jsonPath("$.address").value("Rua A, 123"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = repository.save(new Customer(null, "Lucas Souza", "lucas@email.com", "11922222222", "Rua B, 456"));

        mockMvc.perform(get("/customer/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Lucas Souza"))
                .andExpect(jsonPath("$.email").value("lucas@email.com"))
                .andExpect(jsonPath("$.phone").value("11922222222"))
                .andExpect(jsonPath("$.address").value("Rua B, 456"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = repository.save(new Customer(null, "Pedro Oliveira", "pedro@email.com", "11933333333", "Rua C, 789"));

        customer.setFullName("Pedro Oliveira Silva");
        customer.setAddress("Rua Nova, 999");

        mockMvc.perform(put("/customer/{id}", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Pedro Oliveira Silva"))
                .andExpect(jsonPath("$.address").value("Rua Nova, 999"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Customer customer = repository.save(new Customer(null, "Carlos Pereira", "carlos@email.com", "11944444444", "Rua D, 321"));

        mockMvc.perform(delete("/customer/{id}", customer.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/customer/{id}", customer.getId()))
                .andExpect(status().isNotFound());
    }
}
