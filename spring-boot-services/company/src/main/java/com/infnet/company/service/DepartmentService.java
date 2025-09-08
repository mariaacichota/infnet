package com.infnet.company.service;

import com.infnet.company.model.Department;
import com.infnet.company.repository.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) { this.repo = repo; }

    public List<Department> findAll() { return repo.findAll(); }
    public Department findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id)); }

    public Department create(Department e) {
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when creating Department: " + ex.getMostSpecificCause().getMessage()); }
    }

    public Department update(UUID id, Department e) {
        Department existing = findById(id);
        e.setId(existing.getId());
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when updating Department: " + ex.getMostSpecificCause().getMessage()); }
    }

    public void delete(UUID id) { repo.delete(findById(id)); }
}
