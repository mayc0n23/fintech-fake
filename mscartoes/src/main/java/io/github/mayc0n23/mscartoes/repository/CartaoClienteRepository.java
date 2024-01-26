package io.github.mayc0n23.mscartoes.repository;

import io.github.mayc0n23.mscartoes.entity.CartaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, Long> {

    List<CartaoCliente> findByCpf(String cpf);

}