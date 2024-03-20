package com.Bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Bingo.domain.sorteio.DadosCasdatroSorteio;
import com.Bingo.domain.sorteio.DadosDetalhamentoSorteio;
import com.Bingo.domain.sorteio.DadosListagemSorteio;
import com.Bingo.domain.sorteio.DadosNumerosSorteados;
import com.Bingo.domain.sorteio.Sorteio;
import com.Bingo.domain.sorteio.SorteioRepository;
import com.Bingo.domain.sorteio.SorteioService;
import com.Bingo.domain.usuario.Usuario;
import com.Bingo.domain.usuario.UsuarioService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("sorteio")
public class SorteioController {

	@Autowired
	private SorteioRepository repository;
	
	@Autowired
	private SorteioService service;
		
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody DadosCasdatroSorteio dados, UriComponentsBuilder uriBuilder) {
		Usuario administrador = service.buscarAdministrador(dados.idAdministrador());
		Sorteio sorteio = service.cadastrarSorteio(new Sorteio(dados), administrador);
		
		var uri = uriBuilder.path("/sorteio/{id}").buildAndExpand(sorteio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoSorteio(sorteio));
	}
	
	@GetMapping()
	public ResponseEntity<Page<DadosListagemSorteio>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
		var page = repository.findAll(paginacao).map(DadosListagemSorteio::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Page<DadosListagemSorteio>> listarPorAdministrador(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
		var page = service.listarPorAdministrador(id, paginacao).map(DadosListagemSorteio::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping("/sortear/{id}")
	@Transactional
	public ResponseEntity sortearNumeros(@PathVariable Long id) {
		int numeroSorteado = service.sortearNumero(id);
		
		Sorteio sorteio = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosNumerosSorteados(numeroSorteado, sorteio));
	}

}
