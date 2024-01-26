package io.github.mayc0n23.msavaliadorcredito.domain;

import java.math.BigDecimal;

public class CalculadoraLimiteAprovado {

    public BigDecimal calcular(BigDecimal limite, BigDecimal idade) {
        final var fator = idade.divide(BigDecimal.TEN);
        return fator.multiply(limite);
    }

}