package br.com.ricardo.wallet.api.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ricardo.wallet.api.converter.LancamentoConverter;
import br.com.ricardo.wallet.api.converter.TransferenciaConverter;
import br.com.ricardo.wallet.api.model.LancamentoModel;
import br.com.ricardo.wallet.api.model.input.LancamentoInput;
import br.com.ricardo.wallet.api.model.input.TransferenciaInput;
import br.com.ricardo.wallet.domain.exception.SaldoInsuficienteException;
import br.com.ricardo.wallet.domain.model.Lancamento;
import br.com.ricardo.wallet.domain.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private LancamentoConverter lancamentoConverter;

	@Autowired
	private TransferenciaConverter transferenciaConverter;
	
	@GetMapping("/conta/{idConta}")
	public List<LancamentoModel> findByContaId(@PathVariable Long idConta) {
		return lancamentoConverter.toCollectionModel(lancamentoService.findByContaId(idConta));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<LancamentoModel> create(@RequestBody @Valid LancamentoInput input,
			UriComponentsBuilder uriBuilder) throws SaldoInsuficienteException {
		Lancamento lancamento = lancamentoConverter.toDomainObject(input);
		lancamentoService.create(lancamento);
		URI uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri();
		return ResponseEntity.created(uri).body(lancamentoConverter.toModel(lancamento));
	}

	@PostMapping("/transferencia/conta-origem/{idContaOrigem}/conta-destino/{idContaDestino}")
	@Transactional
	public ResponseEntity<?> transfer(@PathVariable Long idContaOrigem, @PathVariable Long idContaDestino,
			@RequestBody @Valid TransferenciaInput input) throws SaldoInsuficienteException {
		Lancamento lancamento = transferenciaConverter.toDomainObject(input);
		lancamentoService.transfer(idContaOrigem, idContaDestino, lancamento);
		return ResponseEntity.noContent().build();
	}

}
