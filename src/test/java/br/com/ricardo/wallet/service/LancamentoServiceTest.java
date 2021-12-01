package br.com.ricardo.wallet.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ricardo.wallet.exception.ContaNotFoundException;
import br.com.ricardo.wallet.model.Conta;
import br.com.ricardo.wallet.model.Lancamento;
import br.com.ricardo.wallet.model.TipoOperacaoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LancamentoServiceTest {
	
	@Autowired
	private LancamentoService lancamentoService;

	@Test(expected = ContaNotFoundException.class)
	public void deveFalhar_AoFazerLancamento_ComContaInexistente() {
		
		Conta conta = new Conta();
		conta.setId(0L);
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setDataLancamento(OffsetDateTime.now());
		lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
		lancamento.setValor(BigDecimal.TEN);
		
		lancamentoService.create(lancamento);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoFazerLancamento_SemValor() {
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setDataLancamento(OffsetDateTime.now());
		lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
		
		lancamentoService.create(lancamento);

	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoFazerLancamento_ComValorMenorOuIgualAZero() {
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setDataLancamento(OffsetDateTime.now());
		lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
		lancamento.setValor(BigDecimal.ZERO);
		
		lancamentoService.create(lancamento);

	}
	
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoFazerLancamento_SemTipoLancamento() {
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setDataLancamento(OffsetDateTime.now());
		lancamento.setValor(BigDecimal.TEN);
		
		lancamentoService.create(lancamento);

	}

}
