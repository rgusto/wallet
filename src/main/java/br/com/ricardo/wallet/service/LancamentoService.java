package br.com.ricardo.wallet.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.wallet.exception.ContaNotFoundException;
import br.com.ricardo.wallet.exception.LancamentoNotFoundException;
import br.com.ricardo.wallet.model.Conta;
import br.com.ricardo.wallet.model.Lancamento;
import br.com.ricardo.wallet.repository.LancamentoRepository;

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
	public Lancamento create(Lancamento lancamentoFinanceiro) {
		Conta conta = contaService.findById(lancamentoFinanceiro.getConta().getId());
		if (conta != null) {
			lancamentoFinanceiro.setDataLancamento(OffsetDateTime.now());
			return lancamentoFinanceiroRepository.save(lancamentoFinanceiro);
		}
		throw new ContaNotFoundException(lancamentoFinanceiro.getConta().getId());
	}


	@Transactional
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
	}

}
