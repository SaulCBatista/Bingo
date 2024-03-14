package com.Bingo.domain.usuario;

import jakarta.validation.constraints.Email;

public record DadosAutenticacaoUsuario(
		@Email
		String email, 
		String senha) {

}
