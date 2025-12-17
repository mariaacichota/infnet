package com.petfriends.almoxarifado.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "reservas_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @Column(nullable = false)
    private Instant criadaEm;

    private Instant atualizadaEm;

    @ElementCollection
    @CollectionTable(
            name = "reservas_estoque_itens",
            joinColumns = @JoinColumn(name = "reserva_id")
    )
    private List<ItemReservado> itens;

    @PrePersist
    void onCreate() {
        criadaEm = Instant.now();
        atualizadaEm = criadaEm;
        if (status == null) {
            status = StatusReserva.PENDENTE;
        }
    }

    @PreUpdate
    void onUpdate() {
        atualizadaEm = Instant.now();
    }

    public enum StatusReserva {
        PENDENTE,
        SEPARADA,
        FALHA_ESTOQUE,
        CANCELADA
    }
}