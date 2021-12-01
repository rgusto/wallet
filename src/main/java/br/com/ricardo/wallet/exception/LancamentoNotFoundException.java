package br.com.ricardo.wallet.exception;

import javax.persistence.EntityNotFoundException;

public class LancamentoNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public LancamentoNotFoundException(String message) {
		super(message);
	}
	
	public LancamentoNotFoundException(Long id) {
		this(String.format("Lançamento de código %d não encontrado.", id));
	}
}
