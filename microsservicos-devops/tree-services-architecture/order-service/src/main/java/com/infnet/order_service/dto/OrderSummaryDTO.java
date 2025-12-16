package com.infnet.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSummaryDTO {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double total;
}