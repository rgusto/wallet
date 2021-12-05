package br.com.ricardo.wallet.api.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardo.wallet.api.model.input.TransferenciaInput;
import br.com.ricardo.wallet.domain.model.Lancamento;

@Component
public class TransferenciaConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Lancamento toDomainObject(TransferenciaInput transferenciaInput) {
		return modelMapper.map(transferenciaInput, Lancamento.class);
	}
	
}
