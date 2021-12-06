package br.com.ricardo.wallet.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.ricardo.wallet.domain.exception.ContaAlreadyExistsException;
import br.com.ricardo.wallet.domain.exception.ContaNotFoundException;
import br.com.ricardo.wallet.domain.model.Cliente;
import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.service.ContaService;

@SpringBootTest
public class ContaServiceTest {
	
	@Autowired
	private ContaService contaService;
	
	@Test
	public void deveFalhar_AoCadastrarConta_SemAgencia() {
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Cliente titular = new Cliente();
			titular.setId(1L);
			
			Conta conta = new Conta();
			conta.setNumero("654321");
			conta.setTitular(titular);
			
			contaService.create(conta);
	  });		
		
	}
	
	
	@Test
	public void deveFalhar_AoCadastrarConta_SemNumero() {
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Cliente titular = new Cliente();
			titular.setId(1L);
			
			Conta conta = new Conta();
			conta.setAgencia("1234");
			conta.setTitular(titular);
			
			contaService.create(conta);
	  });		
		
	}	

	@Test
	public void deveFalhar_AoCadastrarConta_JaCadastrada() {
		
		Assertions.assertThrows(ContaAlreadyExistsException.class, () -> {
			Cliente titular = new Cliente();
			titular.setId(1L);
			
			Conta conta1 = new Conta();
			conta1.setAgencia("4321");
			conta1.setNumero("123456");
			conta1.setTitular(titular);

			contaService.create(conta1);
			
			Conta conta2 = new Conta();
			conta2.setAgencia("4321");
			conta2.setNumero("123456");
			conta2.setTitular(titular);		
			
			contaService.create(conta2);
	  });	
		
	}	
	
	@Test
	public void deveFalhar_AoExcluirConta_EmUso() {
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			Conta conta = new Conta();
			conta.setId(1L);
			
			contaService.delete(conta.getId());
	  });			
				
	}	
	
	@Test
	public void deveFalhar_AoExcluirConta_Inexistente() {
	
		Assertions.assertThrows(ContaNotFoundException.class, () -> {
			Conta conta = new Conta();
			conta.setId(0L);
			
			contaService.delete(conta.getId());
	  });			
		
	}	
	
}
