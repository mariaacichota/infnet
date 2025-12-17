package com.petfriends.almoxarifado.event;

import java.util.List;

public record PedidoFechadoParaAlmoxarifadoEvent(
        Long pedidoId,
        Long clienteId,
        List<ItemPedidoEvent> itens
) {}
