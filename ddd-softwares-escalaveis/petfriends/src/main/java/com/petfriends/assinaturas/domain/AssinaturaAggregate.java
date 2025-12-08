package com.petfriends.assinaturas.domain;

import com.petfriends.assinaturas.commands.CriarAssinaturaCommand;
import com.petfriends.assinaturas.event.AssinaturaCriadaEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AssinaturaAggregate {

    @AggregateIdentifier
    private String id;

    private Long clienteId;
    private Long petId;
    private String periodicidade;
    private String descricaoPlano;

    protected AssinaturaAggregate() {}

    @CommandHandler
    public AssinaturaAggregate(CriarAssinaturaCommand command) {
        AssinaturaCriadaEvent event = new AssinaturaCriadaEvent(
                command.getAggregateId(),
                command.getClienteId(),
                command.getPetId(),
                command.getPeriodicidade(),
                command.getDescricaoPlano()
        );
        apply(event);
    }

    @EventSourcingHandler
    public void on(AssinaturaCriadaEvent event) {
        this.id = event.getAggregateId();
        this.clienteId = event.getClienteId();
        this.petId = event.getPetId();
        this.periodicidade = event.getPeriodicidade();
        this.descricaoPlano = event.getDescricaoPlano();
    }
}
