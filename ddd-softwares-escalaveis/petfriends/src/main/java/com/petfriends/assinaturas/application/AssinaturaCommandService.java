package com.petfriends.assinaturas.application;

import com.petfriends.assinaturas.commands.CriarAssinaturaCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AssinaturaCommandService {

    private final CommandGateway commandGateway;

    public AssinaturaCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> criarAssinatura(Long clienteId,
                                                     Long petId,
                                                     String periodicidade,
                                                     String descricaoPlano) {

        String aggregateId = UUID.randomUUID().toString();

        CriarAssinaturaCommand command = new CriarAssinaturaCommand(
                aggregateId,
                clienteId,
                petId,
                periodicidade,
                descricaoPlano
        );

        return commandGateway.send(command);
    }
}
