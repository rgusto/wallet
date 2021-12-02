package br.com.ricardo.wallet.service;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.ricardo.wallet.exception.ContaAlreadyExistsException;
import br.com.ricardo.wallet.exception.ContaNotFoundException;
import br.com.ricardo.wallet.model.Cliente;
import br.com.ricardo.wallet.model.Conta;

@SpringBootTest
public class ContaServiceTest {
	
	@Autowired
	private ContaService contaService;
	
	@Test
	public void deveFalhar_AoCadastrarConta_SemAgencia() {
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
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
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
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
