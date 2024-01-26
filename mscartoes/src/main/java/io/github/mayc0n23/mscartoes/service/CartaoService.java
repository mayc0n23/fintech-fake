package io.github.mayc0n23.mscartoes.service;

import io.github.mayc0n23.mscartoes.entity.Cartao;
import io.github.mayc0n23.mscartoes.exception.EntityNotFoundException;
import io.github.mayc0n23.mscartoes.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao cadastrar(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> buscarCartoesComRendaAte(Long renda) {
        final var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

    public Cartao buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));
    }

}