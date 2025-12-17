package com.petfriends.almoxarifado.event;

public record EnderecoEntregaEvent(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep
) {}