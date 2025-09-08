package com.infnet.company.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"sku"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer quantity;
}