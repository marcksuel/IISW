package com.iisw.projeto.services.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	/*
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	*/
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<StandardError> tokenHandler(TokenException e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {
		String error = "Exception error";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	public ResponseEntity<StandardError> doHandleException(Exception e, HttpServletRequest request) {
		if (e instanceof TokenException) {
			return tokenHandler((TokenException) e, request);
		} else {
			return exception( e, request);
		}
	}
	

}