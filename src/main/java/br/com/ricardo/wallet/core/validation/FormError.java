package br.com.ricardo.wallet.core.validation;

public class FormError {
	
	private String field;
	private String message;
	
	public FormError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
