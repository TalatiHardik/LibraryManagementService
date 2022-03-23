package com.lib.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex) {
		return new ResponseEntity<>("book doesn't exist", HttpStatus.NOT_FOUND);
	}

}
