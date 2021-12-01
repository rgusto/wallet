package br.com.ricardo.wallet.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ricardo.wallet.model.Lancamento;
import br.com.ricardo.wallet.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping("/conta/{idConta}")
	public List<Lancamento> findByContaId(@PathVariable Long idConta) {
		return lancamentoService.findByContaId(idConta);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Lancamento> create(@RequestBody @Valid Lancamento form, UriComponentsBuilder uriBuilder) {	
		Lancamento lancamento = lancamentoService.create(form);
		URI uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri();
		return ResponseEntity.created(uri).body(lancamento);
	}
	
}
