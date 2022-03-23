package com.lib.WalletMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE , reason="Insufficient Balance in wallet")
public class InsufficientBalanceException extends Exception {
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
