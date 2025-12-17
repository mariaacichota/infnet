package com.petfriends.almoxarifado.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfriends.almoxarifado.domain.ItemReservado;
import com.petfriends.almoxarifado.domain.ReservaEstoque;
import com.petfriends.almoxarifado.domain.ReservaEstoqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoAlmoxarifadoEventListener {

    private final ReservaEstoqueRepository reservaRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = AlmoxarifadoMessagingConfig.TOPIC_PEDIDOS_ALMOXARIFADO,
            groupId = "almoxarifado-group",
            containerFactory = "almoxarifadoKafkaListenerContainerFactory"
    )
    public void onMessage(String messageJson) {
        try {
            PedidoFechadoParaAlmoxarifadoEvent event =
                    objectMapper.readValue(messageJson, PedidoFechadoParaAlmoxarifadoEvent.class);

            log.info("Recebido evento de pedido para almoxarifado: {}", event);

            ReservaEstoque reserva = ReservaEstoque.builder()
                    .pedidoId(event.pedidoId())
                    .itens(event.itens().stream()
                            .map(i -> ItemReservado.builder()
                                    .produtoId(i.produtoId())
                                    .quantidade(i.quantidade())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();

            reservaRepository.save(reserva);

        } catch (Exception e) {
            log.error("Erro ao processar evento de almoxarifado", e);
        }
    }
}