package io.github.mayc0n23.mscartoes.model.request;

import java.math.BigDecimal;

public record DadosSolicitacaoEmissaoCartaoRequest(
        Long idCartao,
        String cpf,
        String endereco,
        BigDecimal limiteLiberado
) {
}
