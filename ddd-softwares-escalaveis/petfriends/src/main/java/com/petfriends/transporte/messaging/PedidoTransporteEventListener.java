package com.petfriends.transporte.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfriends.transporte.domain.EnderecoEntrega;
import com.petfriends.transporte.domain.Entrega;
import com.petfriends.transporte.domain.EntregaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoTransporteEventListener {

    private final EntregaRepository entregaRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = TransporteMessagingConfig.TOPIC_PEDIDOS_TRANSPORTE,
            groupId = "transporte-group",
            containerFactory = "transporteKafkaListenerContainerFactory"
    )
    public void onMessage(String messageJson) {
        try {
            PedidoProntoParaEnvioEvent event =
                    objectMapper.readValue(messageJson, PedidoProntoParaEnvioEvent.class);

            log.info("Recebido evento de pedido para transporte: {}", event);

            EnderecoEntrega endereco = EnderecoEntrega.builder()
                    .logradouro(event.enderecoEntrega().logradouro())
                    .numero(event.enderecoEntrega().numero())
                    .complemento(event.enderecoEntrega().complemento())
                    .bairro(event.enderecoEntrega().bairro())
                    .cidade(event.enderecoEntrega().cidade())
                    .uf(event.enderecoEntrega().uf())
                    .cep(event.enderecoEntrega().cep())
                    .build();

            Entrega entrega = Entrega.builder()
                    .pedidoId(event.pedidoId())
                    .enderecoEntrega(endereco)
                    .build();

            entregaRepository.save(entrega);

        } catch (Exception e) {
            log.error("Erro ao processar evento de transporte", e);
        }
    }
}
