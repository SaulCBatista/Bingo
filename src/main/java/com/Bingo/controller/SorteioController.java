package com.Bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Bingo.domain.sorteio.DadosCasdatroSorteio;
import com.Bingo.domain.sorteio.DadosDetalhamentoSorteio;
import com.Bingo.domain.sorteio.Sorteio;
import com.Bingo.domain.sorteio.SorteioRepository;
import com.Bingo.domain.sorteio.SorteioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
		Sorteio sorteio = service.cadastrarSorteio(new Sorteio(dados));
		
		var uri = uriBuilder.path("/sorteio/{id}").buildAndExpand(sorteio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoSorteio(sorteio));
	}
	
}
