package com.Bingo.domain.service;

import com.Bingo.domain.cartela.Cartela;

public interface CartelaServiceInterface {

	Cartela cadastrar();
	
	int[] gerarNumeros();
	
}
