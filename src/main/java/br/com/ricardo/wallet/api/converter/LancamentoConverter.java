package br.com.ricardo.wallet.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardo.wallet.api.model.LancamentoModel;
import br.com.ricardo.wallet.api.model.input.LancamentoInput;
import br.com.ricardo.wallet.domain.model.Lancamento;

@Component
public class LancamentoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public LancamentoModel toModel(Lancamento lancamento) {
		return modelMapper.map(lancamento, LancamentoModel.class);
	}

	public List<LancamentoModel> toCollectionModel(List<Lancamento> lancamentos) {
		return lancamentos.stream()
				.map(lancamento -> this.toModel(lancamento))
				.collect(Collectors.toList());
	}

	public Lancamento toDomainObject(LancamentoInput lancamentoInput) {
		return modelMapper.map(lancamentoInput, Lancamento.class);
	}
	
}
