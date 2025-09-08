package com.infnet.company.controller;

import com.infnet.company.model.Department;
import com.infnet.company.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class DepartmentController {
    private final DepartmentService svc;
    public DepartmentController(DepartmentService svc) { this.svc = svc; }

    @GetMapping
    public List<Department> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public Department get(@PathVariable UUID id) { return svc.findById(id); }

    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody Department body) {
        Department created = svc.create(body);
        return ResponseEntity.created(URI.create("/api/department/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable UUID id, @Valid @RequestBody Department body) { return svc.update(id, body); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}