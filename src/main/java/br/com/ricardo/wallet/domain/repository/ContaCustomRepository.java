package br.com.ricardo.wallet.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.query.Param;

public interface ContaCustomRepository {
	
    public BigDecimal getSaldo(@Param("id") Long id);

}
