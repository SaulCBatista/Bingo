package com.Bingo.domain.sorteio;

import java.time.LocalDateTime;

public record DadosCasdatroSorteio(
		Tipo tipo,
		
		int quantidadeDeCartelas,
		
		LocalDateTime dataSorteio) {

}
