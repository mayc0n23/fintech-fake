package io.github.mayc0n23.msavaliadorcredito.clients.model;

import java.math.BigDecimal;

public record CartaoResponse(
        Long id,
        String nome,
        String bandeira,
        BigDecimal renda,
        BigDecimal limite
) {
}