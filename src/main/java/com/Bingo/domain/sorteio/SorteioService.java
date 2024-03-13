package com.Bingo.domain.sorteio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bingo.domain.cartela.Cartela;
import com.Bingo.domain.cartela.CartelaService;
import com.Bingo.domain.service.SorteioServiceInterface;

@Service
public class SorteioService implements SorteioServiceInterface{
	
	@Autowired
	private CartelaService cartela;
	
	@Autowired
	private SorteioRepository repository;
	
	@Override
	public Sorteio cadastrarSorteio(Sorteio sorteio) {
		List<Cartela> cartelas = gerarCartelas(sorteio.getQuantidadeDeCartelas(), sorteio.getCartelas(), sorteio);
		sorteio.setCartelas(cartelas);
		return repository.save(sorteio);
	}

	@Override
	public List<Cartela> gerarCartelas(int quantidadeDeCartelas, List<Cartela> cartelas, Sorteio sorteio) {
		for(int i = 0; i < quantidadeDeCartelas;i++) {
			Cartela cartelaSorteada = cartela.gerar();
			cartelaSorteada.setSorteio(sorteio);
			cartelas.add(cartelaSorteada);
		}
		
		return cartela.salvarTodas(cartelas);
	}
	
}
