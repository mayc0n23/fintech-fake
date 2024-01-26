package io.github.mayc0n23.mscartoes.entity;

import io.github.mayc0n23.mscartoes.domain.BandeiraCartao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal renda;

    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    public Cartao(String nome, BigDecimal renda, BigDecimal limite, BandeiraCartao bandeira) {
        this.nome = nome;
        this.renda = renda;
        this.limite = limite;
        this.bandeira = bandeira;
    }

}