package io.github.mayc0n23.msclientes.controller;

import io.github.mayc0n23.msclientes.mapper.ClienteMapper;
import io.github.mayc0n23.msclientes.model.request.CriarClienteRequest;
import io.github.mayc0n23.msclientes.model.response.ClienteResponse;
import io.github.mayc0n23.msclientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/status")
    public String status() {
        log.info("Obtendo o status do microserviço de clientes");
        return "online";
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid CriarClienteRequest request) {
        final var cliente = ClienteMapper.toEntity(request);
        final var response = ClienteMapper.toResponse(clienteService.cadastrar(cliente));

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(response.cpf())
                .toUri();

        return ResponseEntity.created(headerLocation).body(response);
    }

    @GetMapping(params = "cpf")
    public ClienteResponse buscarPorCpf(@RequestParam("cpf") String cpf) {
        return ClienteMapper.toResponse(clienteService.buscarPorCpf(cpf));
    }

}