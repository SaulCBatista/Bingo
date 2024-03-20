package com.Bingo.domain.sorteio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.cartela.DadosListagemCartela;

public record DadosListagemSorteio(Long id, Tipo tipo, List<DadosListagemCartela>cartelas, int quantidadeDeCartelas, List<Integer> numerosSorteados, LocalDateTime dataInicio, LocalDateTime dataSorteio, Long administrador, boolean ativo) {

	public DadosListagemSorteio(Sorteio sorteio) {
		this(sorteio.getId(), sorteio.getTipo(), obterIdsCartelas(sorteio.getCartelas()), sorteio.getQuantidadeDeCartelas(), sorteio.getNumerosSorteados(), sorteio.getDataInicio(), sorteio.getDataSorteio(), sorteio.getAdministrador().getId(), sorteio.isAtivo());
	}

	private static List<DadosListagemCartela> obterIdsCartelas(List<Cartela> cartelas) {
		return cartelas.stream().map(DadosListagemCartela::new).collect(Collectors.toList());
	}
	
}
