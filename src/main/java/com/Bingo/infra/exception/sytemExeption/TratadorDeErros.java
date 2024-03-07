package com.Bingo.infra.exception.sytemExeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(BusinessRuleExeption.class)
	public ResponseEntity tratarErrorEmailExistente(BusinessRuleExeption e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity tratarErrorAunteticacao(AuthenticationException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
