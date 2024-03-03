package com.Bingo.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String nome) {

	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome());
	}
	
}
