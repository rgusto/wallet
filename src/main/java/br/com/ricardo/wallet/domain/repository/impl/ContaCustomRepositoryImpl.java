package br.com.ricardo.wallet.domain.repository.impl;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ricardo.wallet.domain.repository.ContaCustomRepository;

public class ContaCustomRepositoryImpl implements ContaCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public BigDecimal getSaldo(Long id) {
	
		StringBuilder query = new StringBuilder();
		
		query.append("select coalesce(sum(l.valor),0) from Lancamento l where conta.id = ").append(id).append(" and tipoOperacao = 0");
		BigDecimal entrada = (BigDecimal) entityManager.createQuery(query.toString()).getSingleResult();
		
		StringBuilder query2 = new StringBuilder();
		query2.append("select coalesce(sum(l.valor),0) from Lancamento l where conta.id = ").append(id).append(" and tipoOperacao = 1");
		BigDecimal saida = (BigDecimal) entityManager.createQuery(query2.toString()).getSingleResult();

		return entrada.subtract(saida);
		
	}


}
