package br.com.ricardo.wallet.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.ricardo.wallet.domain.exception.ClienteAlreadyExistsException;
import br.com.ricardo.wallet.domain.exception.ClienteNotFoundException;
import br.com.ricardo.wallet.domain.model.Cliente;
import br.com.ricardo.wallet.domain.service.ClienteService;

@SpringBootTest
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void deveFalhar_AoCadastrarCliente_SemNome() {
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Cliente cliente = new Cliente();
			cliente = clienteService.create(cliente);
	  });		
		
	}
	
	@Test
	public void deveFalhar_AoCadastrarCliente_SemCpf() {
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Fulano");
			cliente = clienteService.create(cliente);
	  });	
		
	}
	
	@Test
	public void deveFalhar_AoCadastrarCliente_ComCpfExistente() {
		
		Assertions.assertThrows(ClienteAlreadyExistsException.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setNome("Fulano");
			cliente.setCpf("22222222222");
			cliente = clienteService.create(cliente);
	  });	
		
	}
	
	@Test
	public void deveFalhar_AoExcluirCliente_EmUso() {
				
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setId(1L);
			
			clienteService.delete(cliente.getId());
	  });	
			
	}	
	
	@Test
	public void deveFalhar_AoExcluirCliente_Inexistente() {
		
		Assertions.assertThrows(ClienteNotFoundException.class, () -> {
			Cliente cliente = new Cliente();
			cliente.setId(0L);
			
			clienteService.delete(cliente.getId());
	  });	
		
	}	
	
}
