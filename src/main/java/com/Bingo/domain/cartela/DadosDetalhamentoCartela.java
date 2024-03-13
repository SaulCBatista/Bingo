package com.Bingo.domain.cartela;

public record DadosDetalhamentoCartela(Long id, int[] numeros,Long idDoSorteio,boolean disponivel) {

	public DadosDetalhamentoCartela(Cartela cartela) {
		this(cartela.getId(), cartela.getNumeros(), cartela.getSorteio().getId(), cartela.isDisponivel());
	}

}
