package io.github.mayc0n23.msavaliadorcredito.clients;

import io.github.mayc0n23.msavaliadorcredito.clients.model.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fintech-fake-ms-clientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ClienteResponse findByCpf(@RequestParam("cpf") String cpf);

}