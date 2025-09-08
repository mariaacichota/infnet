package com.infnet.company.controller;

import com.infnet.company.model.Product;
import com.infnet.company.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class ProductController {
    private final ProductService svc;
    public ProductController(ProductService svc) { this.svc = svc; }

    @GetMapping
    public List<Product> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable UUID id) { return svc.findById(id); }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product body) {
        Product created = svc.create(body);
        return ResponseEntity.created(URI.create("/api/product/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable UUID id, @Valid @RequestBody Product body) { return svc.update(id, body); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}