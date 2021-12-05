package br.com.ricardo.wallet.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.model.TipoOperacaoEnum;

public class LancamentoInput {

	@NotNull
	private Conta conta;

	@NotNull
	private TipoOperacaoEnum tipoOperacao;

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal valor;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoOperacaoEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
