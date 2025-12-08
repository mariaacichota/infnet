package com.petfriends.assinaturas.query;

import com.petfriends.assinaturas.query.AssinaturaRepository;
import com.petfriends.assinaturas.query.AssinaturaView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssinaturaQueryService {

    private final AssinaturaRepository repository;

    public AssinaturaQueryService(AssinaturaRepository repository) {
        this.repository = repository;
    }

    public List<AssinaturaView> listarTodas() {
        return repository.findAll();
    }

    public AssinaturaView buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada: " + id));
    }
}
