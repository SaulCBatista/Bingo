package com.Bingo.domain.service;

import java.util.List;

import com.Bingo.domain.cartela.Cartela;

public interface CartelaServiceInterface {

	Cartela gerar();
	
	List<Cartela> salvarTodas(List<Cartela> cartelas);
	
	int[] gerarNumeros();
	
}
