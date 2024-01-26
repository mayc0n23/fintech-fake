package io.github.mayc0n23.msclientes.mapper;

import io.github.mayc0n23.msclientes.entity.Cliente;
import io.github.mayc0n23.msclientes.model.request.CriarClienteRequest;
import io.github.mayc0n23.msclientes.model.response.ClienteResponse;

public class ClienteMapper {

    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getIdade()
        );
    }

    public static Cliente toEntity(CriarClienteRequest request) {
        return new Cliente(
                request.cpf(),
                request.nome(),
                request.idade()
        );
    }

}