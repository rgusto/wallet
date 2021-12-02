package br.com.ricardo.wallet.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ricardo.wallet.exception.ClienteAlreadyExistsException;
import br.com.ricardo.wallet.exception.ClienteNotFoundException;
import br.com.ricardo.wallet.exception.ContaAlreadyExistsException;
import br.com.ricardo.wallet.exception.ContaNotFoundException;

@RestControllerAdvice
public class ValidationHandler {
	
	@Autowired
	private MessageSource messageSource;


	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormError> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<FormError> list = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormError erro = new FormError(e.getField(), mensagem);
			list.add(erro);
		});
		
		return list;

	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseError handleClienteNotFoundException(ClienteNotFoundException exception) {
		ResponseError responseError = new ResponseError(404, exception.getMessage());
		return responseError;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ContaNotFoundException.class)
	public ResponseError handleContaNotFoundException(ContaNotFoundException exception) {
		ResponseError responseError = new ResponseError(404, exception.getMessage());
		return responseError;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ClienteAlreadyExistsException.class)
	public ResponseError handleClienteAlreadyExistsException(ClienteAlreadyExistsException exception) {
		ResponseError responseError = new ResponseError(400, exception.getMessage());
		return responseError;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ContaAlreadyExistsException.class)
	public ResponseError handleContaAlreadyExistsException(ContaAlreadyExistsException exception) {
		ResponseError responseError = new ResponseError(400, exception.getMessage());
		return responseError;
	}

}
