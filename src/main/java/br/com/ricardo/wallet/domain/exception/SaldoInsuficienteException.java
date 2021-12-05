package br.com.ricardo.wallet.domain.exception;

public class SaldoInsuficienteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String message) {
		super(message);
	}
	
	public SaldoInsuficienteException() {
		this("Saldo insuficiente.");
	}
}
