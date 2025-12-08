package com.petfriends.assinaturas.api;

import com.petfriends.assinaturas.query.AssinaturaQueryService;
import com.petfriends.assinaturas.query.AssinaturaView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaQueryController {

    private final AssinaturaQueryService queryService;

    public AssinaturaQueryController(AssinaturaQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<AssinaturaView>> listarTodas() {
        return ResponseEntity.ok(queryService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaView> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(queryService.buscarPorId(id));
    }
}
