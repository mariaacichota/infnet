package com.infnet.crud_service.controller;

import com.infnet.crud_service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Integer, Product> products = new HashMap<>();

    public ProductController() {
        products.put(1, new Product(1, "Notebook", 3500.00));
        products.put(2, new Product(2, "Teclado", 150.00));
        products.put(3, new Product(3, "Mouse", 80.00));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        if (products.containsKey(product.getId())) {
            return ResponseEntity.badRequest().build();
        }
        products.put(product.getId(), product);
        return ResponseEntity.status(201).body(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product updated) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        updated.setId(id);
        products.put(id, updated);
        return ResponseEntity.ok(updated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        products.remove(id);
        return ResponseEntity.noContent().build();
    }
}
