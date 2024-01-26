package io.github.mayc0n23.msavaliadorcredito.service;

import io.github.mayc0n23.msavaliadorcredito.clients.CartoesResourceClient;
import io.github.mayc0n23.msavaliadorcredito.clients.ClienteResourceClient;
import io.github.mayc0n23.msavaliadorcredito.clients.model.CartaoResponse;
import io.github.mayc0n23.msavaliadorcredito.clients.model.CartoesByClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.clients.model.ClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.domain.CalculadoraLimiteAprovado;
import io.github.mayc0n23.msavaliadorcredito.exception.ErroSolicitacaoCartaoException;
import io.github.mayc0n23.msavaliadorcredito.mapper.AvaliadorCreditoMapper;
import io.github.mayc0n23.msavaliadorcredito.model.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.mayc0n23.msavaliadorcredito.model.response.AvaliacaoClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.CartaoAprovadoResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.ProtocoloSolicitacaoCartaoResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.SituacaoClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.mq.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;

    private final CartoesResourceClient cartoesResourceClient;

    private final SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public SituacaoClienteResponse realizarConsulta(String cpf) {
        final var cliente = getCliente(cpf);
        final var cartoes = getCartoesPorCliente(cpf);
        return AvaliadorCreditoMapper.from(cliente, cartoes);
    }

    public AvaliacaoClienteResponse realizarAvaliacao(String cpf, Long renda) {
        final var cliente = getCliente(cpf);
        final var cartoes = getCartoesComRendaAte(renda);

        final var calculadora = new CalculadoraLimiteAprovado();

        final var cartoesAprovados = cartoes.stream()
                .map(cartao -> new CartaoAprovadoResponse(cartao.nome(), cartao.bandeira(),
                        calculadora.calcular(cartao.limite(), BigDecimal.valueOf(cliente.idade()))))
                .toList();

        return new AvaliacaoClienteResponse(cartoesAprovados);
    }

    private ClienteResponse getCliente(String cpf) {
        return clienteResourceClient.findByCpf(cpf);
    }

    private List<CartoesByClienteResponse> getCartoesPorCliente(String cpf) {
        return cartoesResourceClient.getCartoesPorCliente(cpf);
    }

    private List<CartaoResponse> getCartoesComRendaAte(Long renda) {
        return cartoesResourceClient.getCartoesComRendaAte(renda);
    }

    public ProtocoloSolicitacaoCartaoResponse solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoRequest dados) {
        try {
            solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            final var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartaoResponse(protocolo);
        } catch (Exception ex) {
            throw new ErroSolicitacaoCartaoException(ex.getMessage());
        }
    }

}