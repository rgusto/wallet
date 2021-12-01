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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ricardo.wallet.model.Conta;
import br.com.ricardo.wallet.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public List<Conta> findAll(@RequestParam(required = false) String nome) {
		return contaService.findAll(nome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> findById(@PathVariable Long id) {
		Conta conta = contaService.findById(id);
		if (conta != null) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping("/agencia/{agencia}/numero/{numero}")
	public ResponseEntity<Conta> findByAgenciaNumero(@PathVariable String agencia, @PathVariable String numero) {
		Conta conta = contaService.findByAgenciaNumero(agencia, numero);
		if (conta != null) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.notFound().build(); 		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Conta> create(@RequestBody @Valid Conta form, UriComponentsBuilder uriBuilder) {	
		Conta conta = contaService.create(form);
		URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(conta);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta form) {
		return ResponseEntity.ok(contaService.update(id, form));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		contaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
