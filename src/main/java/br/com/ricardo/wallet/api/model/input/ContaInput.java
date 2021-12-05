package br.com.ricardo.wallet.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ricardo.wallet.domain.model.Cliente;

public class ContaInput {

	@NotBlank
	private String agencia;

	@NotBlank
	private String numero;

	@NotNull
	private Cliente titular;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

}
