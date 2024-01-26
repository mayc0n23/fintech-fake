package io.github.mayc0n23.msavaliadorcredito.mapper;

import io.github.mayc0n23.msavaliadorcredito.clients.model.CartoesByClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.clients.model.ClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.CartaoClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.DadosClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.SituacaoClienteResponse;

import java.util.List;

public class AvaliadorCreditoMapper {

    public static SituacaoClienteResponse from(ClienteResponse clienteResponse, List<CartoesByClienteResponse> cartoesClienteResponse) {
        final var dadosCliente = new DadosClienteResponse(clienteResponse.id(), clienteResponse.nome());
        final var cartoesCliente = toCartoesCliente(cartoesClienteResponse);
        return new SituacaoClienteResponse(dadosCliente, cartoesCliente);
    }

    private static List<CartaoClienteResponse> toCartoesCliente(List<CartoesByClienteResponse> cartoes) {
        return cartoes.stream()
                .map(cartao -> new CartaoClienteResponse(cartao.nome(), cartao.bandeira(), cartao.limite()))
                .toList();
    }

}