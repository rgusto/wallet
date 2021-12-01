package br.com.ricardo.wallet.model;

public enum TipoOperacaoEnum {

	ENTRADA(0, "Entrada"),
	SAIDA(1, "Saída");
	
	private int id;
	private String descricao;
		
	TipoOperacaoEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}	
	
}
