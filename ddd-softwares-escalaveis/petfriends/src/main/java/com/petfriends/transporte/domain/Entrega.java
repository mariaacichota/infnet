package com.petfriends.transporte.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "entregas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntrega status;

    @Embedded
    private EnderecoEntrega enderecoEntrega;

    private String codigoRastreio;

    private Instant criadaEm;
    private Instant atualizadaEm;

    @PrePersist
    void onCreate() {
        criadaEm = Instant.now();
        atualizadaEm = criadaEm;
        if (status == null) {
            status = StatusEntrega.AGUARDANDO_COLETA;
        }
    }

    @PreUpdate
    void onUpdate() {
        atualizadaEm = Instant.now();
    }

    public enum StatusEntrega {
        AGUARDANDO_COLETA,
        EM_TRANSITO,
        ENTREGUE,
        DEVOLVIDA,
        EXTRAVIADA
    }
}