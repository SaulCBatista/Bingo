package com.Bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.cartela.CartelaRepository;
import com.Bingo.domain.cartela.CartelaService;
import com.Bingo.domain.cartela.DadosCartelaComprada;
import com.Bingo.domain.cartela.DadosCompraCartela;
import com.Bingo.domain.cartela.DadosDetalhamentoCartela;
import com.Bingo.domain.cartela.DadosListagemCartela;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("cartela")
public class CartelaController {
	
	@Autowired
	private CartelaRepository repository;
	
	@Autowired
	private CartelaService service;
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemCartela>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		var page = service.listar(paginacao).map(DadosListagemCartela::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var cartela = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoCartela(cartela));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity comprar(@PathVariable Long id, @RequestBody DadosCompraCartela dados) {
		Cartela cartela = service.comprarCartela(id, dados);
		
		return ResponseEntity.ok(new DadosCartelaComprada(cartela));
	}
	
}
