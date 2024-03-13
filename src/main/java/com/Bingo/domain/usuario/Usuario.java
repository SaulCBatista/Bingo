package com.Bingo.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	public Usuario(DadosCadastroUsuario dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.senha = dados.senha();
	}

	public void atualizarDados(DadosAtualizacaoUsuario dados) {
		if(!dados.nome().isBlank()) {
			this.nome = dados.nome();
		}
		
		if(!dados.senha().isBlank()) {
			this.senha = dados.senha();
		}
	}
}
