package br.com.ricardo.wallet.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ricardo.wallet.domain.exception.ContaNotFoundException;
import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.model.Lancamento;
import br.com.ricardo.wallet.domain.model.TipoOperacaoEnum;
import br.com.ricardo.wallet.domain.service.LancamentoService;

@SpringBootTest
public class LancamentoServiceTest {

	@Autowired
	private LancamentoService lancamentoService;

	@Test
	public void deveFalhar_AoFazerLancamento_ComContaInexistente() {

		Assertions.assertThrows(ContaNotFoundException.class, () -> {
			Conta conta = new Conta();
			conta.setId(0L);

			Lancamento lancamento = new Lancamento();
			lancamento.setConta(conta);
			lancamento.setDataLancamento(OffsetDateTime.now());
			lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
			lancamento.setValor(BigDecimal.TEN);

			lancamentoService.create(lancamento);
		});

	}

	@Test
	public void deveFalhar_AoFazerLancamento_SemValor() {

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			Conta conta = new Conta();
			conta.setId(1L);

			Lancamento lancamento = new Lancamento();
			lancamento.setConta(conta);
			lancamento.setDataLancamento(OffsetDateTime.now());
			lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);

			lancamentoService.create(lancamento);
		});

	}

	@Test
	public void deveFalhar_AoFazerLancamento_ComValorMenorOuIgualAZero() {

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			Conta conta = new Conta();
			conta.setId(1L);

			Lancamento lancamento = new Lancamento();
			lancamento.setConta(conta);
			lancamento.setDataLancamento(OffsetDateTime.now());
			lancamento.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
			lancamento.setValor(BigDecimal.ZERO);

			lancamentoService.create(lancamento);
		});

	}

	@Test
	public void deveFalhar_AoFazerLancamento_SemTipoLancamento() {

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			Conta conta = new Conta();
			conta.setId(1L);

			Lancamento lancamento = new Lancamento();
			lancamento.setConta(conta);
			lancamento.setDataLancamento(OffsetDateTime.now());
			lancamento.setValor(BigDecimal.TEN);

			lancamentoService.create(lancamento);
		});

	}

}
