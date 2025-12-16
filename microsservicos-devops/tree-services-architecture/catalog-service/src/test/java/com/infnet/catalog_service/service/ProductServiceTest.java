package com.infnet.catalog_service.service;

import com.infnet.catalog_service.model.Product;
import com.infnet.catalog_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private final ProductRepository repo = Mockito.mock(ProductRepository.class);
    private final ProductService service = new ProductService(repo);

    @Test
    void shouldReturnAllProducts() {
        when(repo.findAll()).thenReturn(List.of(new Product(1L, "P1", "Desc", 10.0)));

        List<Product> result = service.findAll();

        assertThat(result).hasSize(1);
        verify(repo, times(1)).findAll();
    }

    @Test
    void shouldFindProductById() {
        Product p = new Product(1L, "P1", "Desc", 10.0);
        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Product result = service.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void shouldCreateProduct() {
        Product p = new Product(null, "P1", "Desc", 10.0);
        Product saved = new Product(1L, "P1", "Desc", 10.0);
        when(repo.save(p)).thenReturn(saved);

        Product result = service.create(p);

        assertThat(result.getId()).isNotNull();
        verify(repo).save(p);
    }
}
