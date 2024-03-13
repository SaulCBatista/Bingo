package com.Bingo.domain.service;

import java.util.List;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.sorteio.Sorteio;

public interface SorteioServiceInterface {
	
	public Sorteio cadastrarSorteio(Sorteio sorteio); 
	
	public List<Cartela> gerarCartelas(int quantidadeDeCartelas, List<Cartela> cartelas, Sorteio idDoSorteio);	
	
}
