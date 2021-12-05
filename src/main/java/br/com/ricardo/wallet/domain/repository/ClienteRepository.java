package br.com.ricardo.wallet.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ricardo.wallet.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByCpf(String cpf);
	public List<Cliente> findByNome(String nome);
	
}
