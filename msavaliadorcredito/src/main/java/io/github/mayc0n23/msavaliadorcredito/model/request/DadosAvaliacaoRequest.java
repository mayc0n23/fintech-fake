package io.github.mayc0n23.msavaliadorcredito.model.request;

import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.br.CPF;

public record DadosAvaliacaoRequest(
        @CPF
        String cpf,
        @Min(100)
        Long renda) { }