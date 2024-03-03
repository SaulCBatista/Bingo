package com.Bingo.domain.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bingo.domain.service.UsuarioServiceInterface;
import com.Bingo.infra.exception.sytemExeption.AuthenticationException;
import com.Bingo.infra.exception.sytemExeption.BusinessRuleExeption;


@Service
public class UsuarioService implements UsuarioServiceInterface {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario auteticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new AuthenticationException("couldn't possible find a user");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new AuthenticationException("invalid password");
		}
		
		return usuario.get();
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean isExits = repository.existsByEmail(email);
		
		if(isExits) {
			throw new BusinessRuleExeption("this email already exits");
		}
	}

	
	
}
