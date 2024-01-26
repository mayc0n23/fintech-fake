package io.github.mayc0n23.mscartoes.controller;

import io.github.mayc0n23.mscartoes.mapper.CartaoClienteMapper;
import io.github.mayc0n23.mscartoes.mapper.CartaoMapper;
import io.github.mayc0n23.mscartoes.model.request.CriarCartaoRequest;
import io.github.mayc0n23.mscartoes.model.response.CartaoResponse;
import io.github.mayc0n23.mscartoes.model.response.CartoesPorClienteResponse;
import io.github.mayc0n23.mscartoes.service.CartaoClienteService;
import io.github.mayc0n23.mscartoes.service.CartaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    private final CartaoClienteService cartaoClienteService;

    @GetMapping("/status")
    public String status() {
        return "online";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid CriarCartaoRequest request) {
        final var cartao = CartaoMapper.toEntity(request);
        cartaoService.cadastrar(cartao);
    }

    @GetMapping(params = "renda")
    public List<CartaoResponse> getCartoesComRendaAte(@RequestParam("renda") Long renda) {
        return CartaoMapper.toResponseList(cartaoService.buscarCartoesComRendaAte(renda));
    }

    @GetMapping(params = "cpf")
    public List<CartoesPorClienteResponse> getCartoesPorCliente(@RequestParam("cpf") String cpf) {
        return CartaoClienteMapper.toResponseList(cartaoClienteService.getCartoesPorCpf(cpf));
    }

}