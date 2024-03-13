package com.Bingo.domain.cartela;

public record DadosListagemCartela(Long id, int[] numeros, Long idDoSorteio, boolean disponivel) {

	public DadosListagemCartela(Cartela cartela) {
		this(cartela.getId(), cartela.getNumeros(), cartela.getSorteio().getId(), cartela.isDisponivel());
	}
	
}
