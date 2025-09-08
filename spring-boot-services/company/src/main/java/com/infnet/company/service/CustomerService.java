package com.infnet.company.service;

import com.infnet.company.model.Customer;
import com.infnet.company.repository.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) { this.repo = repo; }

    public List<Customer> findAll() { return repo.findAll(); }
    public Customer findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id)); }

    public Customer create(Customer e) {
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when creating Customer: " + ex.getMostSpecificCause().getMessage()); }
    }

    public Customer update(UUID id, Customer e) {
        Customer existing = findById(id);
        e.setId(existing.getId());
        try { return repo.save(e); }
        catch (DataIntegrityViolationException ex) { throw new ConflictException("Conflict when updating Customer: " + ex.getMostSpecificCause().getMessage()); }
    }

    public void delete(UUID id) { repo.delete(findById(id)); }
}
