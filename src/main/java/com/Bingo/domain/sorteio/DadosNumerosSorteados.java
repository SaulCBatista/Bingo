package com.Bingo.domain.sorteio;

import java.util.List;

public record DadosNumerosSorteados(int numeroSorteado, List<Integer> todosOsNumerosSorteados) {

	public DadosNumerosSorteados(int numeroSorteado, Sorteio sorteio) {
		this(numeroSorteado, sorteio.getNumerosSorteados());
	}
	
}
