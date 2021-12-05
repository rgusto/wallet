package br.com.ricardo.wallet.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.model.TipoOperacaoEnum;

public class LancamentoModel {
	
	private Long id;
	private Conta conta;
	private OffsetDateTime dataLancamento;
	private TipoOperacaoEnum tipoOperacao;
	private BigDecimal valor;

	public void setId(Long id) {
		this.id = id;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setDataLancamento(OffsetDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public Conta getConta() {
		return conta;
	}
	
	public OffsetDateTime getDataLancamento() {
		return dataLancamento;
	}

	public TipoOperacaoEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public BigDecimal getValor() {
		return valor;
	}


}
