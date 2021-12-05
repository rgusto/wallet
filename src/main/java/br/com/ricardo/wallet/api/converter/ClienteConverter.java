package br.com.ricardo.wallet.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardo.wallet.api.model.ClienteModel;
import br.com.ricardo.wallet.api.model.input.ClienteInput;
import br.com.ricardo.wallet.domain.model.Cliente;

@Component
public class ClienteConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}

	public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream()
				.map(cliente -> this.toModel(cliente))
				.collect(Collectors.toList());
	}

	public Cliente toDomainObject(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
}
