package io.github.mayc0n23.msavaliadorcredito.model.response;

import java.math.BigDecimal;

public record CartaoAprovadoResponse(String cartao, String bandeira, BigDecimal limiteAprovado) { }