package com.petfriends.almoxarifado.event;

public record PedidoProntoParaEnvioEvent(
        Long pedidoId,
        Long clienteId,
        EnderecoEntregaEvent enderecoEntrega
) {}
