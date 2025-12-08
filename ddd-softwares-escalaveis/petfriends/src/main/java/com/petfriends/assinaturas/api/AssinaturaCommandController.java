package com.petfriends.assinaturas.api;

import com.petfriends.assinaturas.application.AssinaturaCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaCommandController {

    private final AssinaturaCommandService commandService;

    public AssinaturaCommandController(AssinaturaCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<CompletableFuture<String>> criarAssinatura(@RequestBody CriarAssinaturaRequest request) {
        CompletableFuture<String> result = commandService.criarAssinatura(
                request.getClienteId(),
                request.getPetId(),
                request.getPeriodicidade(),
                request.getDescricaoPlano()
        );
        return ResponseEntity.accepted().body(result);
    }

    public static class CriarAssinaturaRequest {

        private Long clienteId;
        private Long petId;
        private String periodicidade;
        private String descricaoPlano;

        public CriarAssinaturaRequest() {
        }

        public CriarAssinaturaRequest(Long clienteId, Long petId, String periodicidade, String descricaoPlano) {
            this.clienteId = clienteId;
            this.petId = petId;
            this.periodicidade = periodicidade;
            this.descricaoPlano = descricaoPlano;
        }

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }

        public Long getPetId() {
            return petId;
        }

        public void setPetId(Long petId) {
            this.petId = petId;
        }

        public String getPeriodicidade() {
            return periodicidade;
        }

        public void setPeriodicidade(String periodicidade) {
            this.periodicidade = periodicidade;
        }

        public String getDescricaoPlano() {
            return descricaoPlano;
        }

        public void setDescricaoPlano(String descricaoPlano) {
            this.descricaoPlano = descricaoPlano;
        }
    }

}
