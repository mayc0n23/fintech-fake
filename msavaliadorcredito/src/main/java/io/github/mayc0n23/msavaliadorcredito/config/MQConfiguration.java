package io.github.mayc0n23.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {

    @Value("${mq.queues.emissao-cartoes}")
    private String filaEmissaoCartoes;

    @Bean
    public Queue filaEmissaoCartoes() {
        return new Queue(filaEmissaoCartoes, true);
    }

}