package io.github.mayc0n23.msavaliadorcredito.model.response;

import java.util.List;

public record SituacaoClienteResponse(DadosClienteResponse cliente, List<CartaoClienteResponse> cartoes) { }