package br.com.gilson.apigateway.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.gilson.apigateway.exceptions.BadRequestException;
import br.com.gilson.apigateway.exceptions.ExceptionResponse;
import br.com.gilson.apigateway.exceptions.UnauthorizedRequestException;
import br.com.gilson.apigateway.exceptions.UnprocessableRequestException;

@ControllerAdvice
@RestController
public class CustomizedRequestResponseEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleBadExceptions(Exception ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnprocessableRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleUprocessableExceptions(Exception ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(UnauthorizedRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleUnauthorizedExceptions(Exception ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

}
