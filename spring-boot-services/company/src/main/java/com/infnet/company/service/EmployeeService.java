package com.infnet.company.service;
import com.infnet.company.model.Employee;
import com.infnet.company.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public List<Employee> findAll() { return repo.findAll(); }
    public Employee findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id)); }

    public Employee create(Employee e) {
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when creating Employee: " + ex.getMostSpecificCause().getMessage()); }
    }

    public Employee update(UUID id, Employee e) {
        Employee existing = findById(id);
        e.setId(existing.getId());
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when updating Employee: " + ex.getMostSpecificCause().getMessage()); }
    }

    public void delete(UUID id) { repo.delete(findById(id)); }
}