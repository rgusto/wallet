package br.com.ricardo.wallet.api.model.input;

import javax.validation.constraints.NotBlank;

public class ClienteInput {

	@NotBlank
	private String nome;

	@NotBlank
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
