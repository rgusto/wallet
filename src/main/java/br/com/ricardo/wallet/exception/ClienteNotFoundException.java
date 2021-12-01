package br.com.ricardo.wallet.exception;

import javax.persistence.EntityNotFoundException;

public class ClienteNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(String message) {
		super(message);
	}
	
	public ClienteNotFoundException(Long id) {
		this(String.format("Cliente de código %d não encontrado.", id));
	}
}
