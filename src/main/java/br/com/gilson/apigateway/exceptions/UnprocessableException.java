package br.com.gilson.apigateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends RuntimeException {

	public UnprocessableException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
