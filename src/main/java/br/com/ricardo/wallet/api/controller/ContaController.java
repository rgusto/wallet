package br.com.ricardo.wallet.api.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ricardo.wallet.api.converter.ContaConverter;
import br.com.ricardo.wallet.api.model.ContaModel;
import br.com.ricardo.wallet.api.model.input.ContaInput;
import br.com.ricardo.wallet.domain.model.Conta;
import br.com.ricardo.wallet.domain.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@Autowired 
	private ContaConverter contaConverter;
	
	@GetMapping
	public List<ContaModel> findAll(@RequestParam(required = false) String nome) {
		return contaConverter.toCollectionModel(contaService.findAll(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaModel> findById(@PathVariable Long id) {
		Conta conta = contaService.findById(id);
		if (conta != null) {
			return ResponseEntity.ok(contaConverter.toModel(conta));
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping("/agencia/{agencia}/numero/{numero}")
	public ResponseEntity<ContaModel> findByAgenciaNumero(@PathVariable String agencia, @PathVariable String numero) {
		Conta conta = contaService.findByAgenciaNumero(agencia, numero);
		if (conta != null) {
			return ResponseEntity.ok(contaConverter.toModel(conta));
		}
		return ResponseEntity.notFound().build(); 		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContaModel> create(@RequestBody @Valid ContaInput input, UriComponentsBuilder uriBuilder) {	
		Conta conta = contaConverter.toDomainObject(input);
		contaService.create(conta);
		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(contaConverter.toModel(conta));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaModel> update(@PathVariable Long id, @RequestBody @Valid ContaInput input) {
		Conta conta = contaConverter.toDomainObject(input);
		return ResponseEntity.ok(contaConverter.toModel(contaService.update(id, conta)));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		contaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/saldo/{id}")
	public ResponseEntity<BigDecimal> getSaldo(@PathVariable Long id) {
		return ResponseEntity.ok(contaService.getSaldo(id));
	}
	
}
