package br.com.ricardo.wallet.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.wallet.domain.exception.ContaNotFoundException;
import br.com.ricardo.wallet.domain.exception.SaldoInsuficienteException;
import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.model.Lancamento;
import br.com.ricardo.wallet.domain.model.TipoOperacaoEnum;
import br.com.ricardo.wallet.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoFinanceiroRepository;

	@Autowired
	private ContaService contaService;

	public List<Lancamento> findAll() {
		return lancamentoFinanceiroRepository.findAll();
	}

	public Lancamento findById(Long id) {
		return lancamentoFinanceiroRepository.findById(id).orElse(null);
	}

	public List<Lancamento> findByContaId(Long idConta) {
		return lancamentoFinanceiroRepository.findByContaId(idConta);
	}

	@Transactional
	public Lancamento create(Lancamento lancamentoFinanceiro) throws SaldoInsuficienteException {
		Conta conta = contaService.findById(lancamentoFinanceiro.getConta().getId());
		if (conta != null) {
			
			if (lancamentoFinanceiro.getTipoOperacao() == TipoOperacaoEnum.SAIDA && 
					lancamentoFinanceiro.getValor().compareTo(contaService.getSaldo(conta.getId())) == 1) {
				throw new SaldoInsuficienteException();
			} else {
				lancamentoFinanceiro.setDataLancamento(OffsetDateTime.now());
				return lancamentoFinanceiroRepository.save(lancamentoFinanceiro);
			}
			
		}
		throw new ContaNotFoundException(lancamentoFinanceiro.getConta().getId());
	}

	/*@Transactional
	public Lancamento update(Long id, Lancamento lancamentoFinanceiro) {
		Lancamento lancamentoFinanceiroDb = this.findById(id);
		if (lancamentoFinanceiroDb != null) {

			Conta conta = contaService.findById(lancamentoFinanceiro.getConta().getId());
			if (conta == null) {
				throw new ContaNotFoundException(lancamentoFinanceiro.getConta().getId());
			}

			BeanUtils.copyProperties(lancamentoFinanceiro, lancamentoFinanceiroDb, "id");
			return lancamentoFinanceiroRepository.save(lancamentoFinanceiroDb);
		} else {
			throw new LancamentoNotFoundException(id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Lancamento lancamentoFinanceiroDb = this.findById(id);
		if (lancamentoFinanceiroDb != null) {
			lancamentoFinanceiroRepository.delete(lancamentoFinanceiroDb);
		} else {
			throw new LancamentoNotFoundException(id);
		}
	}*/

	@Transactional
	public void transfer(Long idContaOrigem, Long idContaDestino, Lancamento lancamentoFinanceiro)
			throws SaldoInsuficienteException {
		Conta contaOrigem = contaService.findById(idContaOrigem);
		if (contaOrigem != null) {

			if (lancamentoFinanceiro.getValor().compareTo(contaService.getSaldo(contaOrigem.getId())) == 1) {
				throw new SaldoInsuficienteException();
			}

			lancamentoFinanceiro.setDataLancamento(OffsetDateTime.now());
			lancamentoFinanceiro.setTipoOperacao(TipoOperacaoEnum.SAIDA);
			lancamentoFinanceiro.setConta(contaOrigem);
			lancamentoFinanceiroRepository.save(lancamentoFinanceiro);

			Conta contaDestino = contaService.findById(idContaDestino);
			if (contaDestino != null) {
				lancamentoFinanceiro.setDataLancamento(OffsetDateTime.now());
				lancamentoFinanceiro.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
				lancamentoFinanceiro.setConta(contaDestino);
				lancamentoFinanceiroRepository.save(lancamentoFinanceiro);
			} else {
				throw new ContaNotFoundException(idContaDestino);
			}
		} else {
			throw new ContaNotFoundException(lancamentoFinanceiro.getConta().getId());
		}
	}
	

}
