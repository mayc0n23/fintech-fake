package io.github.mayc0n23.mscartoes.mapper;

import io.github.mayc0n23.mscartoes.entity.CartaoCliente;
import io.github.mayc0n23.mscartoes.model.response.CartoesPorClienteResponse;

import java.util.List;

public class CartaoClienteMapper {

    public static CartoesPorClienteResponse toResponse(CartaoCliente cartaoCliente) {
        return new CartoesPorClienteResponse(
                cartaoCliente.getCartao().getNome(),
                cartaoCliente.getCartao().getBandeira(),
                cartaoCliente.getLimite()
        );
    }

    public static List<CartoesPorClienteResponse> toResponseList(List<CartaoCliente> cartoesCliente) {
        return cartoesCliente.stream()
                .map(CartaoClienteMapper::toResponse)
                .toList();
    }

}