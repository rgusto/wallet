package br.com.ricardo.wallet.exception;

import javax.persistence.EntityExistsException;

public class ClienteAlreadyExistsException extends EntityExistsException {
	
	private static final long serialVersionUID = 1L;

	public ClienteAlreadyExistsException(String message) {
		super(message);
	}
	
	public ClienteAlreadyExistsException(Long id, String cpf) {
		this(String.format("Já existe um cliente cadastrado com o cpf %s.", cpf));
	}
}
