package com.infnet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.company.model.Product;
import com.infnet.company.repository.ProductRepository;
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
class ProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository repository;
    @Autowired private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(null,"Notebook","SKU123",3500.0,10);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andExpect(jsonPath("$.sku").value("SKU123"))
                .andExpect(jsonPath("$.price").value(3500.0))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = repository.save(new Product(null, "Mouse", "SKU999", 80.0, 50));

        mockMvc.perform(get("/product/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"))
                .andExpect(jsonPath("$.sku").value("SKU999"))
                .andExpect(jsonPath("$.price").value(80.0))
                .andExpect(jsonPath("$.quantity").value(50));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = repository.save(new Product(null, "Teclado", "SKU111", 120.0, 5));

        product.setName("Teclado Mecânico");
        product.setPrice(200.0);
        product.setQuantity(15);

        mockMvc.perform(put("/product/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Teclado Mecânico"))
                .andExpect(jsonPath("$.price").value(200.0))
                .andExpect(jsonPath("$.quantity").value(15));
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product product = repository.save(new Product(null, "Cadeira Gamer", "SKU777", 1500.0, 2));

        mockMvc.perform(delete("/product/{id}", product.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/product/{id}", product.getId()))
                .andExpect(status().isNotFound());
    }
}