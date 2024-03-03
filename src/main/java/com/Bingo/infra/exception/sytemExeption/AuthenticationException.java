package com.Bingo.infra.exception.sytemExeption;

public class AuthenticationException extends RuntimeException {
	
	public AuthenticationException(String message) {
		super(message);
	}

}
