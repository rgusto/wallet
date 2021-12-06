package br.com.ricardo.wallet.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ricardo.wallet.api.model.ContaModel;
import br.com.ricardo.wallet.api.model.input.ContaInput;
import br.com.ricardo.wallet.domain.model.Conta;

@SpringBootTest
public class ContaConverterTest {

	@Autowired
	private ContaConverter contaConverter;
	
	@Test
	public void validarDomainObjectToModel() {
		
		Conta conta = new Conta();
		conta.setAgencia("1234");
		conta.setNumero("654321");
		
		ContaModel contaModel = contaConverter.toModel(conta);
		
		assertThat(contaModel).isNotNull();
		
	}
	
	@Test
	public void validarInputToDomainObject() {
		
		ContaInput contaInput = new ContaInput();
		contaInput.setAgencia("1234");
		contaInput.setNumero("654321");
		
		Conta conta = contaConverter.toDomainObject(contaInput);
		
		assertThat(conta).isNotNull();
		
	}
	
}
