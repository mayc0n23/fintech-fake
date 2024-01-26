package io.github.mayc0n23.msavaliadorcredito.clients;

import io.github.mayc0n23.msavaliadorcredito.clients.model.CartaoResponse;
import io.github.mayc0n23.msavaliadorcredito.clients.model.CartoesByClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fintech-fake-ms-cartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    List<CartoesByClienteResponse> getCartoesPorCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    List<CartaoResponse> getCartoesComRendaAte(@RequestParam("renda") Long renda);

}
