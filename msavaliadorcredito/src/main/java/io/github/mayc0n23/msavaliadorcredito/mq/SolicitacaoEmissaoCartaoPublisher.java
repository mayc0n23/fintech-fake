package io.github.mayc0n23.msavaliadorcredito.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mayc0n23.msavaliadorcredito.model.request.DadosSolicitacaoEmissaoCartaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final Queue filaEmissaoCartoes;

    public void solicitarCartao(DadosSolicitacaoEmissaoCartaoRequest dados) throws JsonProcessingException {
        final var json = convertToJSON(dados);
        rabbitTemplate.convertAndSend(filaEmissaoCartoes.getName(), json);
    }

    private String convertToJSON(DadosSolicitacaoEmissaoCartaoRequest dados) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }

}