package com.infnet.company.service;

import com.infnet.company.model.Supplier;
import com.infnet.company.repository.SupplierRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SupplierService {
    private final SupplierRepository repo;

    public SupplierService(SupplierRepository repo) { this.repo = repo; }

    public List<Supplier> findAll() { return repo.findAll(); }
    public Supplier findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found: " + id)); }

    public Supplier create(Supplier e) {
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when creating Supplier: " + ex.getMostSpecificCause().getMessage()); }
    }

    public Supplier update(UUID id, Supplier e) {
        Supplier existing = findById(id);
        e.setId(existing.getId());
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when updating Supplier: " + ex.getMostSpecificCause().getMessage()); }
    }

    public void delete(UUID id) { repo.delete(findById(id)); }
}