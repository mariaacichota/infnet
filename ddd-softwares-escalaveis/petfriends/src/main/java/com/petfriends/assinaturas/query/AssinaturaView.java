package com.petfriends.assinaturas.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AssinaturaView {

    @Id
    private String id;

    private Long clienteId;
    private Long petId;
    private String periodicidade;
    private String descricaoPlano;

    public AssinaturaView() {
    }

    public AssinaturaView(String id, Long clienteId, Long petId, String periodicidade, String descricaoPlano) {
        this.id = id;
        this.clienteId = clienteId;
        this.petId = petId;
        this.periodicidade = periodicidade;
        this.descricaoPlano = descricaoPlano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
