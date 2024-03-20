package com.Bingo.domain.sorteio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.cartela.DadosListagemCartela;

public record DadosDetalhamentoSorteio(Long id, Tipo tipo, List<DadosListagemCartela> idDasCartelas, int quantidadeDeCartelas, LocalDateTime dataInicio, LocalDateTime dataSorteio, Long idDoAdministrador, boolean ativo) {
	
	public DadosDetalhamentoSorteio(Sorteio sorteio) {
		this(sorteio.getId(), sorteio.getTipo(), obterIdsCartelas(sorteio.getCartelas()), sorteio.getQuantidadeDeCartelas(), sorteio.getDataInicio(), sorteio.getDataSorteio(), sorteio.getAdministrador().getId(),sorteio.isAtivo());
	}
	
	private static List<DadosListagemCartela> obterIdsCartelas(List<Cartela> cartelas) {
        return cartelas.stream().map(DadosListagemCartela::new).collect(Collectors.toList());
    }
}