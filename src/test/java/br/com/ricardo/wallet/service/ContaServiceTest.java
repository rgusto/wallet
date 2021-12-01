package br.com.ricardo.wallet.service;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ricardo.wallet.exception.ContaAlreadyExistsException;
import br.com.ricardo.wallet.exception.ContaNotFoundException;
import br.com.ricardo.wallet.model.Cliente;
import br.com.ricardo.wallet.model.Conta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaServiceTest {
	
	@Autowired
	private ContaService contaService;
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoCadastrarConta_SemAgencia() {
		
		Cliente titular = new Cliente();
		titular.setId(1L);
		
		Conta conta = new Conta();
		conta.setNumero("654321");
		conta.setTitular(titular);
		
		contaService.create(conta);
		
	}
	
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoCadastrarConta_SemNumero() {
		
		Cliente titular = new Cliente();
		titular.setId(1L);
		
		Conta conta = new Conta();
		conta.setAgencia("1234");
		conta.setTitular(titular);
		
		contaService.create(conta);
		
	}	

	@Test(expected = ContaAlreadyExistsException.class)
	public void deveFalhar_AoCadastrarConta_JaCadastrada() {
		
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
		
	}	
	
	@Test(expected = DataIntegrityViolationException.class)
	public void deveFalhar_AoExcluirConta_EmUso() {
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		contaService.delete(conta.getId());
		
	}	
	
	@Test(expected = ContaNotFoundException.class)
	public void deveFalhar_AoExcluirConta_Inexistente() {
		
		Conta conta = new Conta();
		conta.setId(0L);
		
		contaService.delete(conta.getId());
		
	}	
	
}
