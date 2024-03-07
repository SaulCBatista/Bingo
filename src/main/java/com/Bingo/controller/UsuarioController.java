package com.Bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Bingo.domain.usuario.DadosAtualizacaoUsuario;
import com.Bingo.domain.usuario.DadosCadastroUsuario;
import com.Bingo.domain.usuario.DadosDetalhamentoUsuario;
import com.Bingo.domain.usuario.DadosListagemUsuario;
import com.Bingo.domain.usuario.Usuario;
import com.Bingo.domain.usuario.UsuarioRepository;
import com.Bingo.domain.usuario.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		Usuario usuario = service.cadastrar(new Usuario(dados));
		
		var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { 
		var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
		var usuario = repository.getReferenceById(dados.id());
		usuario.atualizarDados(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
	}
	
}
