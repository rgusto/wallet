package br.com.ricardo.wallet.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;

public class TransferenciaInput {

	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
