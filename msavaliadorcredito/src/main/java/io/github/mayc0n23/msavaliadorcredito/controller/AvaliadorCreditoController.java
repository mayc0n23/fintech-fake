package io.github.mayc0n23.msavaliadorcredito.controller;

import io.github.mayc0n23.msavaliadorcredito.model.request.DadosAvaliacaoRequest;
import io.github.mayc0n23.msavaliadorcredito.model.request.DadosSolicitacaoEmissaoCartaoRequest;
import io.github.mayc0n23.msavaliadorcredito.model.response.AvaliacaoClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.ProtocoloSolicitacaoCartaoResponse;
import io.github.mayc0n23.msavaliadorcredito.model.response.SituacaoClienteResponse;
import io.github.mayc0n23.msavaliadorcredito.service.AvaliadorCreditoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService service;

    @GetMapping("/status")
    public String status() {
        return "online";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public SituacaoClienteResponse realizarConsulta(@RequestParam("cpf") String cpf) {
        return service.realizarConsulta(cpf);
    }

    @PostMapping
    public AvaliacaoClienteResponse realizarAvaliacao(@RequestBody @Valid DadosAvaliacaoRequest dados) {
        return service.realizarAvaliacao(dados.cpf(), dados.renda());
    }

    @PostMapping("/solicitar-cartao")
    public ProtocoloSolicitacaoCartaoResponse solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartaoRequest dados) {
        return service.solicitarEmissaoCartao(dados);
    }

}