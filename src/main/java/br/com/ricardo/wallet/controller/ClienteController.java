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

import br.com.ricardo.wallet.model.Cliente;
import br.com.ricardo.wallet.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> findAll(@RequestParam(required = false) String nome) {
		return clienteService.findAll(nome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> findByCpf(@PathVariable String cpf) {
		Cliente cliente = clienteService.findByCpf(cpf);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build(); 
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente form, UriComponentsBuilder uriBuilder) {	
		clienteService.create(form);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(form.getId()).toUri();
		return ResponseEntity.created(uri).body(form);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente form) {
		return ResponseEntity.ok(clienteService.update(id, form));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
