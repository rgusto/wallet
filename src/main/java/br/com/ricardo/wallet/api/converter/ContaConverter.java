package br.com.ricardo.wallet.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardo.wallet.api.model.ContaModel;
import br.com.ricardo.wallet.api.model.input.ContaInput;
import br.com.ricardo.wallet.domain.model.Conta;

@Component
public class ContaConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public ContaModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaModel.class);
	}

	public List<ContaModel> toCollectionModel(List<Conta> contas) {
		return contas.stream()
				.map(conta -> this.toModel(conta))
				.collect(Collectors.toList());
	}

	public Conta toDomainObject(ContaInput contaInput) {
		return modelMapper.map(contaInput, Conta.class);
	}
}
