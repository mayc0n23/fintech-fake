package io.github.mayc0n23.mscartoes.service;

import io.github.mayc0n23.mscartoes.entity.Cartao;
import io.github.mayc0n23.mscartoes.entity.CartaoCliente;
import io.github.mayc0n23.mscartoes.model.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.mayc0n23.mscartoes.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final CartaoClienteRepository repository;

    private final CartaoService cartaoService;

    public List<CartaoCliente> getCartoesPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Transactional
    public CartaoCliente cadastrar(DadosSolicitacaoEmissaoCartaoRequest dados) {
        final Cartao cartao = cartaoService.buscar(dados.idCartao());
        CartaoCliente cartaoCliente = new CartaoCliente();
        cartaoCliente.setCpf(dados.cpf());
        cartaoCliente.setCartao(cartao);
        cartaoCliente.setLimite(dados.limiteLiberado());
        return repository.save(cartaoCliente);
    }

}