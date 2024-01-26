package io.github.mayc0n23.mscartoes.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mayc0n23.mscartoes.model.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.mayc0n23.mscartoes.service.CartaoClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscriber {

    private final CartaoClienteService cartaoClienteService;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveMessage(@Payload String payload) {
        try {
            final var dados = convertToObject(payload);
            cartaoClienteService.cadastrar(dados);
        } catch (JsonProcessingException ex) {
            log.error("Erro ao receber solicitação de emissão de cartão: {}", ex.getMessage());
        }
    }

    public DadosSolicitacaoEmissaoCartaoRequest convertToObject(String payload) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        return mapper.readValue(payload, DadosSolicitacaoEmissaoCartaoRequest.class);
    }

}