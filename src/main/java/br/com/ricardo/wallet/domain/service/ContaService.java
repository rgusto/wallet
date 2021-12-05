package br.com.ricardo.wallet.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.wallet.domain.exception.ClienteNotFoundException;
import br.com.ricardo.wallet.domain.exception.ContaAlreadyExistsException;
import br.com.ricardo.wallet.domain.exception.ContaNotFoundException;
import br.com.ricardo.wallet.domain.model.Cliente;
import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Conta> findAll(String nome) {
		if (nome == null) {
			return contaRepository.findAll();		
		} else {
			return contaRepository.findByTitularNome(nome);
		}
	}
	
	public Conta findById(Long id) {
		return contaRepository.findById(id).orElse(null);
	}
	
	public Conta findByAgenciaNumero(String agencia, String numero) {
		return contaRepository.findByAgenciaNumero(agencia, numero).orElse(null);
	}
	
	@Transactional
	public Conta create(Conta conta) {
		Conta contaDb = this.findByAgenciaNumero(conta.getAgencia(), conta.getNumero());
		if (contaDb != null) {
			throw new ContaAlreadyExistsException(conta.getAgencia(), conta.getNumero());		
		}		
		
		Cliente cliente = clienteService.findById(conta.getTitular().getId());
		if (cliente != null) {
			return contaRepository.save(conta);			
		}
		throw new ClienteNotFoundException(conta.getTitular().getId());
	}
	
	@Transactional
	public Conta update(Long id, Conta conta) {	
		Conta contaDb = this.findById(id);
		if (contaDb != null) {
			
			Cliente cliente = clienteService.findById(conta.getTitular().getId());
			if (cliente == null) {
				throw new ClienteNotFoundException(conta.getTitular().getId());	
			}			
			
			BeanUtils.copyProperties(conta, contaDb, "id");
			return contaRepository.save(contaDb);
		} else {
			throw new ContaNotFoundException(id);
		}		
	}

	@Transactional
	public void delete(Long id) {
		Conta contaDb = this.findById(id);
		if (contaDb != null) {
			contaRepository.delete(contaDb);
		} else {
			throw new ContaNotFoundException(id);
		}
	}
	
	public BigDecimal getSaldo(Long id) {
		Conta contaDb = this.findById(id);
		if (contaDb != null) {
			return contaRepository.getSaldo(id);
		} else {
			throw new ContaNotFoundException(id);
		}		
	}
}
