package com.Bingo.domain.cartela;

public record DadosCartelaComprada(Long id, int[] numeros, Long idDoSorteio, Long idDoComprador) {

	public DadosCartelaComprada(Cartela cartela) {
		this(cartela.getId(), cartela.getNumeros(), cartela.getSorteio().getId(), cartela.getComprador().getId());
	}
	
}
