package com.infnet.order_service.service;

import com.infnet.order_service.client.CatalogClient;
import com.infnet.order_service.dto.OrderSummaryDTO;
import com.infnet.order_service.dto.ProductDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final CatalogClient catalogClient;

    public OrderService(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    public Mono<OrderSummaryDTO> getOrderSummary(Long productId, Integer quantity) {
        return catalogClient.getProductById(productId)
                .map(p -> buildSummary(p, quantity));
    }

    private OrderSummaryDTO buildSummary(ProductDTO product, Integer quantity) {
        double total = product.getPrice() * quantity;
        return new OrderSummaryDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                total
        );
    }
}
