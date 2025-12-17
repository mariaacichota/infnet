package com.petfriends.almoxarifado.event;

public record ItemPedidoEvent(
        Long produtoId,
        Integer quantidade
) {}