package br.com.ricardo.wallet.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Conta conta;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataLancamento;
	
	private TipoOperacaoEnum tipoOperacao;
	
	//@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}	
	
	public OffsetDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(OffsetDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
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
