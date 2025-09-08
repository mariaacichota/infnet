package com.infnet.company.service;

import com.infnet.company.model.Product;
import com.infnet.company.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> findAll() { return repo.findAll(); }
    public Product findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id)); }

    public Product create(Product e) {
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when creating Product: " + ex.getMostSpecificCause().getMessage()); }
    }

    public Product update(UUID id, Product e) {
        Product existing = findById(id);
        e.setId(existing.getId());
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when updating Product: " + ex.getMostSpecificCause().getMessage()); }
    }

    public void delete(UUID id) { repo.delete(findById(id)); }
}

