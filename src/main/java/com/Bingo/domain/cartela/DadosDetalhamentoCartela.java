package com.Bingo.domain.cartela;


public record DadosDetalhamentoCartela(Long id, int[] numeros, boolean disponivel) {

	public DadosDetalhamentoCartela(Cartela cartela) {
		this(cartela.getId(), cartela.getNumeros(), cartela.isDisponivel());
	}

}
