package com.lib.authorization.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserExistsWithTheGivenCredentialsException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(UserExistsWithTheGivenCredentialsException.class);
	private static final long serialVersionUID = 1L;

	public UserExistsWithTheGivenCredentialsException(String message)
	{
		super(message);
		log.info("UserExistsWithTheGivenCredentials exception was raised !!!!");
	}
	
	
	
	
}
