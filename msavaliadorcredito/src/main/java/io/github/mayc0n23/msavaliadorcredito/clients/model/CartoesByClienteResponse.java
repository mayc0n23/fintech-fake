package io.github.mayc0n23.msavaliadorcredito.clients.model;

import java.math.BigDecimal;

public record CartoesByClienteResponse(String nome, String bandeira, BigDecimal limite) {
}