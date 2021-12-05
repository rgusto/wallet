package br.com.ricardo.wallet.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.wallet.domain.exception.ClienteAlreadyExistsException;
import br.com.ricardo.wallet.domain.exception.ClienteNotFoundException;
import br.com.ricardo.wallet.domain.model.Cliente;
import br.com.ricardo.wallet.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll(String nome) {
		if (nome == null) {
			return clienteRepository.findAll();		
		} else {
			return clienteRepository.findByNome(nome);
		}
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf).orElse(null);
	}
	
	@Transactional
	public Cliente create(Cliente cliente) {
		Cliente clienteDb = this.findByCpf(cliente.getCpf());
		if (clienteDb != null) {
			throw new ClienteAlreadyExistsException(cliente.getId(), cliente.getCpf());		
		}	
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		Cliente clienteDb = this.findById(id);
		if (clienteDb != null) {
			BeanUtils.copyProperties(cliente, clienteDb, "id");
			return clienteRepository.save(clienteDb);
		} else {
			throw new ClienteNotFoundException(id);
		}		
	}

	@Transactional
	public void delete(Long id) {
		Cliente clienteDb = this.findById(id);
		if (clienteDb != null) {
			clienteRepository.delete(clienteDb);
		} else {
			throw new ClienteNotFoundException(id);
		}
	}
	
}
