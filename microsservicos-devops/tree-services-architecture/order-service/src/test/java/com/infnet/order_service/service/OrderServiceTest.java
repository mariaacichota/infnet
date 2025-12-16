package com.infnet.order_service.service;

import com.infnet.order_service.client.CatalogClient;
import com.infnet.order_service.dto.OrderSummaryDTO;
import com.infnet.order_service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Test
    void shouldBuildOrderSummary() {
        CatalogClient client = mock(CatalogClient.class);
        OrderService service = new OrderService(client);

        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Notebook");
        product.setPrice(3000.0);

        when(client.getProductById(1L)).thenReturn(Mono.just(product));

        OrderSummaryDTO summary = service.getOrderSummary(1L, 2).block();

        assertThat(summary.getTotal()).isEqualTo(6000.0);
    }
}
