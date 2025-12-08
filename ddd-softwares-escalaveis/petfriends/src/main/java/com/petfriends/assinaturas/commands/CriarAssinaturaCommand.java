package com.petfriends.assinaturas.commands;

import com.petfriends.shared.commands.BaseCommand;

public class CriarAssinaturaCommand extends BaseCommand {

    private final Long clienteId;
    private final Long petId;
    private final String periodicidade;
    private final String descricaoPlano;

    public CriarAssinaturaCommand(String aggregateId,
                                  Long clienteId,
                                  Long petId,
                                  String periodicidade,
                                  String descricaoPlano) {
        super(aggregateId);
        this.clienteId = clienteId;
        this.petId = petId;
        this.periodicidade = periodicidade;
        this.descricaoPlano = descricaoPlano;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getPetId() {
        return petId;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public String getDescricaoPlano() {
        return descricaoPlano;
    }
}
