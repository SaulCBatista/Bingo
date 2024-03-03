package com.Bingo.domain.service;

import com.Bingo.domain.usuario.Usuario;

public interface UsuarioServiceInterface {
	
	Usuario auteticar(String email, String senha);
	
	Usuario cadastrar(Usuario usuario);
	
	void validarEmail(String email);
	
}
