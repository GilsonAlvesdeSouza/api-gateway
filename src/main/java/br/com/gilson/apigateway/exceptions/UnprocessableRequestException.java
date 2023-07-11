package br.com.gilson.apigateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnprocessableRequestException(String message) {
		super(message);
	}

}
