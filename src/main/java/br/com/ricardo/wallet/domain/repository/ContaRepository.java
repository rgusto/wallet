package br.com.ricardo.wallet.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ricardo.wallet.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>, ContaCustomRepository {

	@Query("select c from Conta c where c.agencia = :agencia and c.numero = :numero")
	public Optional<Conta> findByAgenciaNumero(@Param("agencia") String agencia, @Param("numero") String numero);

	public List<Conta> findByTitularNome(@Param("nome") String nome);

}
