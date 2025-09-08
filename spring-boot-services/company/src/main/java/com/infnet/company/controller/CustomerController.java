package com.infnet.company.controller;

import com.infnet.company.model.Customer;
import com.infnet.company.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class CustomerController {
    private final CustomerService svc;
    public CustomerController(CustomerService svc) { this.svc = svc; }

    @GetMapping
    public List<Customer> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public Customer get(@PathVariable UUID id) { return svc.findById(id); }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer body) {
        Customer created = svc.create(body);
        return ResponseEntity.created(URI.create("/api/customer/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable UUID id, @Valid @RequestBody Customer body) { return svc.update(id, body); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
