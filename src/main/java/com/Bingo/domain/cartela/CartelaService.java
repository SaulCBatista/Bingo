package com.Bingo.domain.cartela;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bingo.domain.gerador.GeradorDeNumerosService;
import com.Bingo.domain.service.CartelaServiceInterface;

@Service
public class CartelaService implements CartelaServiceInterface{

	@Autowired
	private CartelaRepository repository;
	
	@Autowired
	private GeradorDeNumerosService geradorDeNumerosService;
	
	@Override
	public Cartela gerar() {
		int[] numerosDaCartela = gerarNumeros();
		Cartela cartela = new Cartela(numerosDaCartela, true);
		return cartela;
	}

	public List<Cartela> salvarTodas(List<Cartela> cartelas) {
		return repository.saveAll(cartelas);
	}
	
	@Override
	public int[] gerarNumeros() {
		ArrayList<Integer>todosOsNumeros = geradorDeNumerosService.listarTodosOsNumeros();
		int[] numerosSorteadosDaCartela = geradorDeNumerosService.sorterNumerosDeCartela(todosOsNumeros);
		return numerosSorteadosDaCartela;
	}
	
}
