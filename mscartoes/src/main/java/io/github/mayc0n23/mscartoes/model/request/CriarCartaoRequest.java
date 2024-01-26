package io.github.mayc0n23.mscartoes.model.request;

import io.github.mayc0n23.mscartoes.domain.BandeiraCartao;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record CriarCartaoRequest(
        String nome,
        BandeiraCartao bandeira,
        @Min(100)
        BigDecimal renda,
        @Min(100)
        BigDecimal limite
) {
}