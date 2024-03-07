package com.Bingo.domain.cartela;

import java.util.ArrayList;

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
	public Cartela cadastrar() {
		int[] numerosDaCartela = gerarNumeros();
		Cartela cartela = new Cartela(numerosDaCartela, true);
		return repository.save(cartela);
	}

	@Override
	public int[] gerarNumeros() {
		ArrayList<Integer>todosOsNumeros = geradorDeNumerosService.listarTodosOsNumeros();
		int[] numerosSorteadosDaCartela = geradorDeNumerosService.sorterNumerosDeCartela(todosOsNumeros);
		return numerosSorteadosDaCartela;
	}
	
}
