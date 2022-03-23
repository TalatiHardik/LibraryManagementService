package com.lib.WalletMS.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception exception) {
		return "The exception caused is "+exception.getMessage();
	}
}
