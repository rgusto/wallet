package br.com.ricardo.wallet.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ricardo.wallet.api.model.ClienteModel;
import br.com.ricardo.wallet.api.model.input.ClienteInput;
import br.com.ricardo.wallet.domain.model.Cliente;

@SpringBootTest
public class ClienteConverterTest {

	@Autowired
	private ClienteConverter clienteConverter;
	
	@Test
	public void validarDomainObjectToModel() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf("11111111111");
		cliente.setNome("Maria");
		
		ClienteModel clienteModel = clienteConverter.toModel(cliente);
		
		assertThat(clienteModel).isNotNull();
		
	}
	
	@Test
	public void validarInputToDomainObject() {
		
		ClienteInput clienteInput = new ClienteInput();
		clienteInput.setCpf("11111111111");
		clienteInput.setNome("Maria");
		
		Cliente cliente = clienteConverter.toDomainObject(clienteInput);
		
		assertThat(cliente).isNotNull();
		
	}
	
}
