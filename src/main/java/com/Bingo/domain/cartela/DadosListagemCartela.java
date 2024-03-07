package com.Bingo.domain.cartela;

public record DadosListagemCartela(Long id, int[] numeros, boolean disponivel) {

	public DadosListagemCartela(Cartela cartela) {
		this(cartela.getId(), cartela.getNumeros(), cartela.isDisponivel());
	}
	
}
