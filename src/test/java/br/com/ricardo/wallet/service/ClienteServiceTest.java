package br.com.ricardo.wallet.service;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ricardo.wallet.exception.ClienteNotFoundException;
import br.com.ricardo.wallet.model.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoCadastrarCliente_SemNome() {
		
		Cliente cliente = new Cliente();
		cliente = clienteService.create(cliente);
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoCadastrarCliente_SemCpf() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Fulano");
		cliente = clienteService.create(cliente);
		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void deveFalhar_AoExcluirCliente_EmUso() {
		
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		clienteService.delete(cliente.getId());
		
	}	
	
	@Test(expected = ClienteNotFoundException.class)
	public void deveFalhar_AoExcluirCliente_Inexistente() {
		
		Cliente cliente = new Cliente();
		cliente.setId(0L);
		
		clienteService.delete(cliente.getId());
		
	}	
	
}
