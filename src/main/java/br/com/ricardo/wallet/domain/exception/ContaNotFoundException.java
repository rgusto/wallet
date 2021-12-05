package br.com.ricardo.wallet.domain.exception;

import javax.persistence.EntityNotFoundException;

public class ContaNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ContaNotFoundException(String message) {
		super(message);
	}
	
	public ContaNotFoundException(Long id) {
		this(String.format("Conta de código %d não encontrada.", id));
	}
}
