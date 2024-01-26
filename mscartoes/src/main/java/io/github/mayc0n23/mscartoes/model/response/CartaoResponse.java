package io.github.mayc0n23.mscartoes.model.response;

import io.github.mayc0n23.mscartoes.domain.BandeiraCartao;

import java.math.BigDecimal;

public record CartaoResponse(
        Long id,
        String nome,
        BandeiraCartao bandeira,
        BigDecimal renda,
        BigDecimal limite
) {
}
