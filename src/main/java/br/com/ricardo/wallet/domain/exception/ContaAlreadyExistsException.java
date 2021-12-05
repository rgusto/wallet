package br.com.ricardo.wallet.domain.exception;

import javax.persistence.EntityExistsException;

public class ContaAlreadyExistsException extends EntityExistsException {
	
	private static final long serialVersionUID = 1L;

	public ContaAlreadyExistsException(String message) {
		super(message);
	}
	
	public ContaAlreadyExistsException(String agencia, String numero) {
		this(String.format("Já existe uma conta cadastrada na agência %s com número %s.", agencia, numero));
	}
}
