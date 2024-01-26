package io.github.mayc0n23.msclientes.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.br.CPF;

public record CriarClienteRequest(
        @CPF
        String cpf,
        String nome,
        @Min(18)
        @Max(120)
        Integer idade) {
}