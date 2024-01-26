package io.github.mayc0n23.msavaliadorcredito.model.response;

import java.math.BigDecimal;

public record CartaoClienteResponse(String nome, String bandeira, BigDecimal limiteLiberado) { }