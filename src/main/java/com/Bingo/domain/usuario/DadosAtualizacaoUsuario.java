package com.Bingo.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
		
		@NotNull
		Long id,
		
		@NotBlank
		String nome,
		
		@NotBlank
		String senha) {

}
