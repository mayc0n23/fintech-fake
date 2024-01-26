package io.github.mayc0n23.mscartoes.mapper;

import io.github.mayc0n23.mscartoes.entity.Cartao;
import io.github.mayc0n23.mscartoes.model.request.CriarCartaoRequest;
import io.github.mayc0n23.mscartoes.model.response.CartaoResponse;

import java.util.List;

public class CartaoMapper {

    public static Cartao toEntity(CriarCartaoRequest request) {
        return new Cartao(
            request.nome(),
            request.renda(),
            request.limite(),
            request.bandeira()
        );
    }

    public static CartaoResponse toResponse(Cartao cartao) {
        return new CartaoResponse(
            cartao.getId(),
            cartao.getNome(),
            cartao.getBandeira(),
            cartao.getRenda(),
            cartao.getLimite()
        );
    }

    public static List<CartaoResponse> toResponseList(List<Cartao> cartoes) {
        return cartoes.stream()
            .map(CartaoMapper::toResponse)
            .toList();
    }

}