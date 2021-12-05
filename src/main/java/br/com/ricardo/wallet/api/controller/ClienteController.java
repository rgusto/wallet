package br.com.ricardo.wallet.api.controller;

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

import br.com.ricardo.wallet.api.converter.ClienteConverter;
import br.com.ricardo.wallet.api.model.ClienteModel;
import br.com.ricardo.wallet.api.model.input.ClienteInput;
import br.com.ricardo.wallet.domain.model.Cliente;
import br.com.ricardo.wallet.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private ClienteConverter clienteConverter;

	@GetMapping
	public List<ClienteModel> findAll(@RequestParam(required = false) String nome) {
		return clienteConverter.toCollectionModel(clienteService.findAll(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> findById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente != null) {
			return ResponseEntity.ok(clienteConverter.toModel(cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ClienteModel> findByCpf(@PathVariable String cpf) {
		Cliente cliente = clienteService.findByCpf(cpf);
		if (cliente != null) {
			return ResponseEntity.ok(clienteConverter.toModel(cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteModel> create(@RequestBody @Valid ClienteInput input,
			UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteConverter.toDomainObject(input);
		clienteService.create(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteConverter.toModel(cliente));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteModel> update(@PathVariable Long id, @RequestBody @Valid ClienteInput input) {
		Cliente cliente = clienteConverter.toDomainObject(input);
		return ResponseEntity.ok(clienteConverter.toModel(clienteService.update(id, cliente)));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
