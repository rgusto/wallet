package br.com.ricardo.wallet.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ricardo.wallet.domain.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	public List<Lancamento> findByContaId(Long idConta);
	
}
