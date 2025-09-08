package com.infnet.company.controller;

import com.infnet.company.model.Supplier;
import com.infnet.company.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class SupplierController {
    private final SupplierService svc;
    public SupplierController(SupplierService svc) { this.svc = svc; }

    @GetMapping
    public List<Supplier> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public Supplier get(@PathVariable UUID id) { return svc.findById(id); }

    @PostMapping
    public ResponseEntity<Supplier> create(@Valid @RequestBody Supplier body) {
        Supplier created = svc.create(body);
        return ResponseEntity.created(URI.create("/api/supplier/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable UUID id, @Valid @RequestBody Supplier body) { return svc.update(id, body); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}