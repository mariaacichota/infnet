package com.infnet.company.controller;
import com.infnet.company.model.Employee;
import com.infnet.company.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService svc;
    public EmployeeController(EmployeeService svc) { this.svc = svc; }

    @GetMapping
    public List<Employee> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public Employee get(@PathVariable UUID id) { return svc.findById(id); }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee body) {
        Employee created = svc.create(body);
        return ResponseEntity.created(URI.create("/api/employee/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable UUID id, @Valid @RequestBody Employee body) { return svc.update(id, body); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}