package com.infnet.order_service.controller;

import com.infnet.order_service.dto.OrderSummaryDTO;
import com.infnet.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/summary/{productId}")
    public Mono<OrderSummaryDTO> getSummary(@PathVariable Long productId,
                                            @RequestParam(defaultValue = "1") Integer quantity) {
        return service.getOrderSummary(productId, quantity);
    }
}
